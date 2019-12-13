package godinner.lab.com.godinner.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import godinner.lab.com.godinner.R;
import godinner.lab.com.godinner.model.Mensagem;
import godinner.lab.com.godinner.utils.Data;

public class MessageListAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context context;
    private List<Mensagem> mMensagemList;

    public MessageListAdapter(Context context, List<Mensagem> mMensagemList) {
        this.context = context;
        this.mMensagemList = mMensagemList;
    }

    public void refreshData(Mensagem m){
        mMensagemList.add(m);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Mensagem mensagem = mMensagemList.get(position);

        if (mensagem.getRemetente().equals("C")) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Mensagem mensagem = mMensagemList.get(position);

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) viewHolder).bind(mensagem, position);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) viewHolder).bind(mensagem, position);
                break;
        }
    }

    class SentMessageHolder extends RecyclerView.ViewHolder {

        private TextView messageText;
        private TextView timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
        }

        void bind(Mensagem message, int position) {
            messageText.setText(message.getMessage());
            timeText.setText(message.getCreatedAt());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        private TextView messageText;
        private TextView timeText;
        private TextView nameText;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.text_message_body);
            timeText = itemView.findViewById(R.id.text_message_time);
            nameText = itemView.findViewById(R.id.text_message_name);
        }

        void bind(Mensagem message, int position) {
            nameText.setText(message.getUsername());
            messageText.setText(message.getMessage());
            timeText.setText(message.getCreatedAt());
        }
    }

    @Override
    public int getItemCount() {
        return mMensagemList != null ? mMensagemList.size() : 0;
    }
}
