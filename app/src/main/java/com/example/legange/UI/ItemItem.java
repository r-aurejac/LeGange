package com.example.legange.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.legange.Item.ActiveItem;
import com.example.legange.Item.Item;
import com.example.legange.Player.Player;
import com.example.legange.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemItem extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ITEM= "param1";
    private static final String PLAYER= "param2";
    Button showButton;
    TextView nameTV;

    // TODO: Rename and change types of parameters
    private Item item;
    private Player player;


    public ItemItem() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ItemItem newInstance(Item item,Player player) {
        ItemItem fragment = new ItemItem();
        Bundle args = new Bundle();
        args.putSerializable(ITEM, item);
        args.putSerializable(PLAYER,player);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (Item) getArguments().getSerializable(ITEM);
            player = (Player) getArguments().getSerializable(PLAYER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_item, container, false);
        nameTV = view.findViewById(R.id.name_tv);
        showButton = view.findViewById(R.id.show_button);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemInfo();
            }
        });
        nameTV.setText(item.getName());
        return view;
    }
    void showItemInfo()
    {
        String message = item.getDescription() + '\n';
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(item instanceof ActiveItem) {
            final ActiveItem activeItem = (ActiveItem) item;
            message += "utilisation restante : " + String.valueOf(activeItem.getDurability());
            builder.setPositiveButton("Utiliser", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if(activeItem.use(player))
                        Toast.makeText(getActivity(),"succès",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(),"echec",Toast.LENGTH_LONG).show();
                }
            });
        }

        builder.setMessage(message)

                .setNegativeButton("retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }
}