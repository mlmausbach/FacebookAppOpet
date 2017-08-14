package br.com.opet.tds.facebookappopet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendsActivity extends Activity {

    private String facebookID;
    ProfilePictureView profile;
    TextView textNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        profile = (ProfilePictureView) findViewById(R.id.profile);
        textNome = (TextView) findViewById(R.id.textNome);

        facebookID = getIntent().getStringExtra("FB_ID");

        AccessToken token = AccessToken.getCurrentAccessToken();

        profile.setProfileId(token.getUserId());

        loadUserName();
    }

    public void loadUserName(){
       new GraphRequest(AccessToken.getCurrentAccessToken(), "me", null,
               HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                JSONObject obj = response.getJSONObject();
                try {
                    String nome = obj.getString("name");
                    textNome.setText("Olá!" + nome);
                }  catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).executeAsync();
    }
}
