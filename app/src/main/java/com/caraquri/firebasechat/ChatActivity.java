package com.caraquri.firebasechat;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.caraquri.firebasechat.databinding.ChatActivityBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

  private ChatActivityBinding binding;

  private FirebaseRecyclerAdapter mAdapter;

  public static Intent createIntent(@NonNull Context context) {
    return new Intent(context, ChatActivity.class);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.chat_activity);

    final String uuid = Uuid.get(this);

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    final RecyclerView recycler = binding.contentLayout.list;
    recycler.setHasFixedSize(true);
    recycler.setLayoutManager(new LinearLayoutManager(this));

    mAdapter =
        new FirebaseRecyclerAdapter<Chat, ChatViewHolder>(Chat.class, ChatViewHolder.LAYOUT, ChatViewHolder.class,
            ref) {
          @Override
          public void populateViewHolder(ChatViewHolder viewHolder, Chat chat, int position) {
            viewHolder.bind(chat);
          }
        };
    recycler.setAdapter(mAdapter);

    RxTextView.textChangeEvents(binding.contentLayout.input).subscribe(new ActionSubscriber<TextViewTextChangeEvent>() {
      @Override
      public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
        binding.contentLayout.enter.setEnabled(textViewTextChangeEvent.count() > 0);
      }
    });

    RxView.clicks(binding.contentLayout.enter).subscribe(new ActionSubscriber<Void>() {
      @Override
      public void onNext(Void v) {
        ref.push().setValue(new Chat("user" + uuid.substring(0, 6), binding.contentLayout.input.getText().toString(), new Date().toString()));
        binding.contentLayout.input.setText("");
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mAdapter.cleanup();
  }
}
