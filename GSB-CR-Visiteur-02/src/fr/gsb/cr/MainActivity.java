package fr.gsb.cr;

import fr.gsb.cr.R;
import fr.gsb.cr.modeles.ModeleGsb;
import fr.gsb.cr.techniques.Session;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextView tvAlert ;
	private EditText etMatricule ;
	private EditText etMDP ;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvAlert = (TextView) findViewById(R.id.tvAlert) ;
		etMatricule = (EditText) findViewById(R.id.etMatricule) ;
		etMDP = (EditText) findViewById(R.id.etMDP) ;
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void seConnecter(View view){
		String matricule = this.etMatricule.getText().toString() ;
		String mdp = this.etMDP.getText().toString() ;
		
		boolean test = Session.ouvrir(matricule, mdp);
		if (test){
			Toast.makeText(this, "OK !"+Session.getSession().getLeVisiteur().getNom()+" "+Session.getSession().getLeVisiteur().getPrenom() , Toast.LENGTH_LONG).show();
			
			Intent intent = new Intent(this, MenuGsbActivity.class);
			startActivity(intent);
		}
		else {
			tvAlert.setText("Visiteur Inconnu");
			this.annuler(null);
		}
		
		
		
	}
	
	public void annuler(View view){
		this.etMatricule.setText("");
		this.etMDP.setText("");
	}
	
}
