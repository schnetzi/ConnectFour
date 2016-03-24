package paul.connectfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Bundle extras = getIntent().getExtras();
        if(extras.containsKey("gameState")) {
            TextView text = (TextView) findViewById(R.id.game_gameOver);
            int gameState = extras.getInt("gameState");

            String textHelper = "Game Over: ";

            if(gameState == Game.YELLOW) {
                text.setText(textHelper + "Player Yellow wins");
            } else if (gameState == Game.RED) {
                text.setText(textHelper + "Player Red wins");
            } else {
                text.setText(textHelper + "Draw");
            }
        }
    }
}
