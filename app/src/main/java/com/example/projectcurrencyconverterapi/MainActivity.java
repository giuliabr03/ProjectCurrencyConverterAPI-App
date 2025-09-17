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
       protected void onCreate(Bundle savedInstanceState){
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);//DEfine o layot da atividade

           //inicializa os componentes da interface
           spinnerMoedaOrigem = findViewById(R.id.spinnerMoedaOrigem);
           spinnerMoedaDestino = findViewById(R.id.spinnerMoedaDestino);
           etValor = findViewById(R.id.etValor);
           btnConverter = findViewById(R.id.btnConverter);
           tvResultado = findViewById(R.id.tvResultado);

           // Configura os spinners com a lista de moedas
           ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moedas);
           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           spinnerMoedaOrigem.setAdapter(adapter);
           spinnerMoedaDestino.setAdapter(adapter);

           // carrega as taxas de cãmbio da API
           carregarTaxasDeCambio();

           //configura o botão de conversão
           btnConverter.setOnClickListener(view -> converterMoeda());

       }
       //metodo para carregar taxas de câmbio da API

       private void carregarTaxasDeCambio(){
           ExchangeRateService service = RetrofitClient.getInstance(); //Obtém a instância do serviço
           Call<ExchangeRatesResponse> call = service.getExchangeRates("2adadf270c12be034c730f50", "USD"); //Faz a chamada da API

           //Executa a chamada de forma assincrona faz uma chamada assincrona enviando
           call.enqueue(new Callback<ExchangeRatesResponse>() {
               @Override
               public void onResponse(Call<ExchangeRatesResponse> call, Response<ExchangeRatesResponse> response) {
                   if (response.isSuccessful() && response.body() != null){
                       taxasDeCambio = response.body().getConversion_rates(); //armazena as taxas de câmbio
                   }else { //exibi mensagem erro
                       tvResultado.setText("Erro ao carregar taxas de câmbio");
                   }
               }
               @Override
               public void onFailure(Call<ExchangeRatesResponse> call, Throwable t) {
                   tvResultado.setText("Erro na conexão");
                   return;
               }

           });

       }
       // Método para converter o valor de uma moeda para outra

       private void converterMoeda(){
           String valorTexto = etValor.getText().toString(); // Obtém o valor digitado pelo usuário

           // Verificar se o valor é válido
           if(valorTexto.isEmpty()){
               Toast.makeText(this, "Digite um valor válido", Toast.LENGTH_SHORT).show();
               return;
           }
           String moedaOrigem = spinnerMoedaOrigem.getSelectedItem().toString(); // Obtém a moeda de origem
           String moedaDestino = spinnerMoedaDestino.getSelectedItem().toString(); // Obtém a moeda de destino
           double valor = Double.parseDouble(valorTexto);

           //Verifica se as taxas de câmbio estão disponíveis
           if(taxasDeCambio == null || taxasDeCambio.containsKey(moedaOrigem) || !taxasDeCambio.containsKey(moedaDestino)){
               tvResultado.setText("Taxas de câmbio indisponíveis");
               return;
           }

           // Obtém as taxas de cambio para moedas selecionadas
           double taxaOrigem = taxasDeCambio.get(moedaOrigem);
           double taxaDestino = taxasDeCambio.get(moedaDestino);

           //Calcula o valor convertido
           double valorConvertido = (valor / taxaOrigem) * taxaDestino;

           //Exibe o resultado da conversão formatada
           tvResultado.setText(String.format("Resultado: %.2f %s", valorConvertido, moedaDestino));
       }
   }