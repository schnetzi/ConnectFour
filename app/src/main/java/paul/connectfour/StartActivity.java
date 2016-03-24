package paul.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ViewGroup vg = (ViewGroup) findViewById(R.id.game_options);

        for (int i = 0; i < vg.getChildCount(); i++) {
            vg.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int player = Integer.parseInt(v.getTag().toString());

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("player", player);
        
        startActivity(intent);
    }
}
