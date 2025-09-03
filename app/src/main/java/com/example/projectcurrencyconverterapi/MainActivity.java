package com.example.projectcurrencyconverterapi;

  import android.os.Bundle;
  import android.view.View;
  import android.widget.ArrayAdapter;
  import android.widget.Button;
  import android.widget.EditText;
  import android.widget.Spinner;
  import android.widget.TextView;
  import android.widget.Toast;
  import androidx.appcompat.app.AppCompatActivity;
  import java.util.List;
  import java.util.Map;
  import retrofit2.Call;
  import retrofit2.Callback;
  import retrofit2.Response;

  //Importando as classes do projeto
   import com.example.projectcurrencyconverterapi.RetrofitClient;
   import com.example.projectcurrencyconverterapi.ExchangeRateService;

   public class MainActivity extends AppCompatActivity{
       // componentes de interface
       private Spinner spinnerMoedaOrigem, spinnerMoedaDestino;
       private EditText etValor;
       private  Button btnConverter;
       private  TextView tvResultado;
       //lista de moedas suportadas

       private List<String> moedas = List.of("USD","BRL","EUR","GBP","JPY");

       //mapa para armazenar as taxa de cambio obtidas da API
       private Map<String, Double> taxasDeCambio;

       @Override
       protected void onCreat(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);//DEfine o layot da atividade
       }


   }

public class MainActivity {
}
