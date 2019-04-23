package com.bowling.edward.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;


public class ItemListAdapter extends RecyclerView.Adapter<ItemListViewHolder> {

    private List<StockItem> stockList;
    private List<String> basket;

    private Context context;

    public ItemListAdapter(){

    }
    public ItemListAdapter(List<StockItem> stockList, List<String> basket, Context context) {
        this.stockList = stockList;
        this.context = context;
        this.basket = basket;

    }


    @Override
    public ItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ItemListViewHolder(layoutView);
    }

    /*
     *       Changed both of Source Compatibility and Target Compatibility to 1.8 so the expressions below would work
     * */

    @Override
    public void onBindViewHolder(final ItemListViewHolder holder, int position) {
        holder.mTitle.setText(stockList.get(position).getTitle());

//        if (basket.contains(usersList.get(position).getItemId())) {
//            holder.mBuy.setText("purchase");
//        } else {
//            holder.mBuy.setText("remove");
//        }
//
//        holder.mBuy.setOnClickListener(view -> {
//            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            if (holder.mBuy.getText().toString().equals("purchase") || !following.contains(usersList.get(holder.getLayoutPosition()).getItemId())) {
//                holder.mBuy.setText("remove");
//                FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("cart").child(usersList.get(holder.getLayoutPosition()).getItemId()).setValue(true);
//            } else {
//                holder.mBuy.setText("purchase");
//                FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("cart").child(usersList.get(holder.getLayoutPosition()).getItemId()).removeValue();
//            }
//        });
//
//        holder.itemView.setOnClickListener(view -> {
//            checkChatId(position);
//        });
    }

    @Override
    public int getItemCount() {
        return this.stockList.size();
    }



 /*   private void checkChatId(int position) {

        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String personId = usersList.get(position).getUid();
        //PUSH CHAT ID IF NOT EXIST
        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference().child("chats");
        chatRef.child(currentUserId).child(personId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    setChatIdToFirebase(chatRef, currentUserId, personId);
                } else {
                    //GET CHAT ID IF EXIST
                    getChatIdFromFirebase(chatRef, currentUserId, personId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void navigateToChatActivity(String userId, String chatId) {
        Intent intent = new Intent(context, ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("chat_id", chatId);
        b.putString("user_id", userId);
        intent.putExtras(b);
        context.startActivity(intent);
    }

    private void setChatIdToFirebase(DatabaseReference chatRef, String currentUserId, String personId){
        String chat_id = FirebaseDatabase.getInstance().getReference().push().getKey();
        chatRef.child(currentUserId).child(personId).child(chat_id).setValue(true);
        chatRef.child(personId).child(currentUserId).child(chat_id).setValue(true).addOnSuccessListener(aVoid -> navigateToChatActivity(personId, chat_id));
    }


    private void getChatIdFromFirebase(DatabaseReference chatRef, String currentUserId, String personId){
        chatRef.child(currentUserId).child(personId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String chatId = "";
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        chatId = ds.getKey();

                    }
                    navigateToChatActivity(personId, chatId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }*/
}

