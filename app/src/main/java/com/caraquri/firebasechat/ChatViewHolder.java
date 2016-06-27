package com.caraquri.firebasechat;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.caraquri.firebasechat.databinding.ChatListItemBinding;

public class ChatViewHolder extends RecyclerView.ViewHolder {

  @LayoutRes
  public static final int LAYOUT = R.layout.chat_list_item;

  ChatListItemBinding binding;

  public ChatViewHolder(View itemView) {
    super(itemView);
    binding = ChatListItemBinding.bind(itemView);
  }

  public void bind(Chat chat) {
    binding.setChat(chat);
  }
}
