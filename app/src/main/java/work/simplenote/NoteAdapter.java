package work.simplenote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Stone on 2017/10/5.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    private int resourceId;
    public NoteAdapter(Context context, int textId, List<Note> objects){
            super(context,textId,objects);
        resourceId = textId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Note note = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){  // listview 优化操作
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.content = (TextView)view.findViewById(R.id.content);
            viewHolder.time = (TextView)view.findViewById(R.id.time);
            viewHolder.id = (TextView)view.findViewById(R.id.id);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.content.setText(note.getContent());
        viewHolder.time.setText(note.getTime());
        viewHolder.id.setText(note.getId());
        viewHolder.id.setVisibility(View.INVISIBLE);
        return view;
//        TextView content = (TextView)view.findViewById(R.id.content);
//        TextView time = (TextView)view.findViewById(R.id.time);
//        TextView id = (TextView)view.findViewById(R.id.id);
//        content.setText(note.getContent());
//        time.setText(note.getTime());
//        id.setText(note.getId());


    }
    class ViewHolder{
        TextView content;
        TextView time;
        TextView id;

    }

}
