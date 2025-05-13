package jdev.mentoria.lojavirtual;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdev.mentoria.lojavirtual.enums.ApiTokenIntegracao;
import jdev.mentoria.lojavirtual.model.dto.EmpresaTransporteDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class TesteAPIMelhorEnvio {

	public static void main(String[] args) throws Exception {
		
		
		
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, "{\n    \"orders\": [\n        \"9e55f440-38c6-4491-8884-18dae893ae58\"\n    ]\n}");
				Request request = new Request.Builder()
				  .url(ApiTokenIntegracao.URL_MELHOR_ENVIO_SAND_BOX+ "api/v2/me/shipment/tracking")
				  .method("POST", body)
				  .addHeader("Accept", "application/json")
				  .addHeader("Content-Type", "application/json")
				  .addHeader("Authorization", "Bearer " +  ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SAND_BOX)
				  .addHeader("User-Agent", "suporte@jdevtreinamento.com.br")
				  .build();
				
				Response response = client.newCall(request).execute();
		
				System.out.println(response.body().string());
				

		
		
		
		
		//6 - traz uma lista de agency (agencias) de transportadoras
		//tipo tem uma AGENCIA da TRANSPORTADORA JDLOG em TIJUCAS
		//uma em SAO JOSE, etc... Agencia e meio como loja...
		//
		//OkHttpClient client = new OkHttpClient();
		//
		//Request request = new Request.Builder()
		  //.url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/agencies?company=2&country=BR&state=SC&city=Joinville")
		  //.get()
		  //.addHeader("accept", "application/json")
		  //.addHeader("User-Agent", "rodrigojosefagundes@gmail.com")
		  //.build();
		  //
		//Response response = client.newCall(request).execute();
		//
		//System.out.println(response.body().string());
		
		
		
		
		
		
		
		
		
		//5 - Faz impressao das etiquetas (pede para a API do
		//MELHORENVIO gerar um link com a etiqueta
		//
		//OkHttpClient client = new OkHttpClient();
		//
		//MediaType mediaType = MediaType.parse("application/json");
		//RequestBody body = RequestBody.create(mediaType, "{\"mode\":\"private\",\"orders\":[\"9e55f440-38c6-4491-8884-18dae893ae58\"]}");
		//Request request = new Request.Builder()
		  //.url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/print")
		  //.post(body)
		  //.addHeader("Accept", "application/json")
		  //.addHeader("Content-Type", "application/json")
		  //.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiIxYTNiZjY5MGFjYmUxZjczZTBhNzc1ZmYzNDM0NmM5YzAyYmFjNDY3MmM3NDBkNDkzMGYzYWM1MjNjMTUzZDEzMzQ0NDQyMzM3ZjFhNGIwYiIsImlhdCI6MTc0MDMzMTIyMC4wNDY3NzEsIm5iZiI6MTc0MDMzMTIyMC4wNDY3NzMsImV4cCI6MTc3MTg2NzIyMC4wMjQ4OTMsInN1YiI6IjllNDgwODhhLWRiNzMtNDkwZS04NDM1LTEyMWMzYzI5OGZmYiIsInNjb3BlcyI6WyJjYXJ0LXJlYWQiLCJjYXJ0LXdyaXRlIiwiY29tcGFuaWVzLXJlYWQiLCJjb21wYW5pZXMtd3JpdGUiLCJjb3Vwb25zLXJlYWQiLCJjb3Vwb25zLXdyaXRlIiwibm90aWZpY2F0aW9ucy1yZWFkIiwib3JkZXJzLXJlYWQiLCJwcm9kdWN0cy1yZWFkIiwicHJvZHVjdHMtZGVzdHJveSIsInByb2R1Y3RzLXdyaXRlIiwicHVyY2hhc2VzLXJlYWQiLCJzaGlwcGluZy1jYWxjdWxhdGUiLCJzaGlwcGluZy1jYW5jZWwiLCJzaGlwcGluZy1jaGVja291dCIsInNoaXBwaW5nLWNvbXBhbmllcyIsInNoaXBwaW5nLWdlbmVyYXRlIiwic2hpcHBpbmctcHJldmlldyIsInNoaXBwaW5nLXByaW50Iiwic2hpcHBpbmctc2hhcmUiLCJzaGlwcGluZy10cmFja2luZyIsImVjb21tZXJjZS1zaGlwcGluZyIsInRyYW5zYWN0aW9ucy1yZWFkIiwidXNlcnMtcmVhZCIsInVzZXJzLXdyaXRlIiwid2ViaG9va3MtcmVhZCIsIndlYmhvb2tzLXdyaXRlIiwid2ViaG9va3MtZGVsZXRlIiwidGRlYWxlci13ZWJob29rIl19.YzcVLme1YLCrDJdGQlG9kjGJwSgy8Ba42GlHTlzlaqHQ957gWpepw4M0burx3ejdrXThuUuMNycLFxrSqaTTiolJqN7XSnlegTLbJ_NGAzQn7QTxaG-o4shLFWtPXVfY6toIxwu0RzoXuRdg9ZFIIKPojwWj0O0TiXAOiI1VIGs-fV_wb5eLxm8bNpWMYGF6qcOdG3MJk3Gt5phkDcUlTjJegqrieJhps4ZW6a_EM3HGI6iNecaies4hVZjfqurGyXwLKK5VChNZLWaHcjhe0ReS0zDHod3zYw1a_zyqVgHnPIfIdOI2MYqDhXLP2w6OYEiAwnZiNaoiM8qdEgcFsSKZA1SlJnpB2fXEK0Tb1KZLJuI9BSKTMvrsAClUMS0QnvptRLEsvgkbiIYlutQNiWIFc_6MIS9YWyn7mgc9ZAKXnlonCyWzMg4yp8RrrKr7Cb7SkU3vzYyuDCjhJfGtf4DtsS5q9sMhYTurJJZ9T1y1_8Hpktz3bZ2AGbhYiDr4FHkE_QhFFr_REl0Y_w5cfXNSVNCENz8aXmKBIL6N0IQI3M622KB4T2FFe9xasejLQlvPeAvNpr1V-GWIWvQDZwEl7I6N9LB3dC9-Hdu6hPn4W0mxCAn0cBYFcr1dQrsmFAyADvDG9QCVt5IDPFtsT_0uTjia-vj9du8M_Nh7mkM")
		  //.addHeader("User-Agent", "rodrigojosefagundes@gmail.com")
		  //.build();

		//Response response = client.newCall(request).execute();
		
		
		//System.out.println(response.body().string());
		
		
		
		
		
		
		//4 - GERACAO DA ETIQUETA
		//
		//OkHttpClient client = new OkHttpClient();
		//
		//MediaType mediaType = MediaType.parse("application/json");
		//RequestBody body = RequestBody.create(mediaType, "{\"orders\":[\"9e55f440-38c6-4491-8884-18dae893ae58\"]}");
		//Request request = new Request.Builder()
		//.url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/generate")
		//.post(body)
		//.addHeader("Accept", "application/json")
		//.addHeader("Content-Type", "application/json")
		//.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiIxYTNiZjY5MGFjYmUxZjczZTBhNzc1ZmYzNDM0NmM5YzAyYmFjNDY3MmM3NDBkNDkzMGYzYWM1MjNjMTUzZDEzMzQ0NDQyMzM3ZjFhNGIwYiIsImlhdCI6MTc0MDMzMTIyMC4wNDY3NzEsIm5iZiI6MTc0MDMzMTIyMC4wNDY3NzMsImV4cCI6MTc3MTg2NzIyMC4wMjQ4OTMsInN1YiI6IjllNDgwODhhLWRiNzMtNDkwZS04NDM1LTEyMWMzYzI5OGZmYiIsInNjb3BlcyI6WyJjYXJ0LXJlYWQiLCJjYXJ0LXdyaXRlIiwiY29tcGFuaWVzLXJlYWQiLCJjb21wYW5pZXMtd3JpdGUiLCJjb3Vwb25zLXJlYWQiLCJjb3Vwb25zLXdyaXRlIiwibm90aWZpY2F0aW9ucy1yZWFkIiwib3JkZXJzLXJlYWQiLCJwcm9kdWN0cy1yZWFkIiwicHJvZHVjdHMtZGVzdHJveSIsInByb2R1Y3RzLXdyaXRlIiwicHVyY2hhc2VzLXJlYWQiLCJzaGlwcGluZy1jYWxjdWxhdGUiLCJzaGlwcGluZy1jYW5jZWwiLCJzaGlwcGluZy1jaGVja291dCIsInNoaXBwaW5nLWNvbXBhbmllcyIsInNoaXBwaW5nLWdlbmVyYXRlIiwic2hpcHBpbmctcHJldmlldyIsInNoaXBwaW5nLXByaW50Iiwic2hpcHBpbmctc2hhcmUiLCJzaGlwcGluZy10cmFja2luZyIsImVjb21tZXJjZS1zaGlwcGluZyIsInRyYW5zYWN0aW9ucy1yZWFkIiwidXNlcnMtcmVhZCIsInVzZXJzLXdyaXRlIiwid2ViaG9va3MtcmVhZCIsIndlYmhvb2tzLXdyaXRlIiwid2ViaG9va3MtZGVsZXRlIiwidGRlYWxlci13ZWJob29rIl19.YzcVLme1YLCrDJdGQlG9kjGJwSgy8Ba42GlHTlzlaqHQ957gWpepw4M0burx3ejdrXThuUuMNycLFxrSqaTTiolJqN7XSnlegTLbJ_NGAzQn7QTxaG-o4shLFWtPXVfY6toIxwu0RzoXuRdg9ZFIIKPojwWj0O0TiXAOiI1VIGs-fV_wb5eLxm8bNpWMYGF6qcOdG3MJk3Gt5phkDcUlTjJegqrieJhps4ZW6a_EM3HGI6iNecaies4hVZjfqurGyXwLKK5VChNZLWaHcjhe0ReS0zDHod3zYw1a_zyqVgHnPIfIdOI2MYqDhXLP2w6OYEiAwnZiNaoiM8qdEgcFsSKZA1SlJnpB2fXEK0Tb1KZLJuI9BSKTMvrsAClUMS0QnvptRLEsvgkbiIYlutQNiWIFc_6MIS9YWyn7mgc9ZAKXnlonCyWzMg4yp8RrrKr7Cb7SkU3vzYyuDCjhJfGtf4DtsS5q9sMhYTurJJZ9T1y1_8Hpktz3bZ2AGbhYiDr4FHkE_QhFFr_REl0Y_w5cfXNSVNCENz8aXmKBIL6N0IQI3M622KB4T2FFe9xasejLQlvPeAvNpr1V-GWIWvQDZwEl7I6N9LB3dC9-Hdu6hPn4W0mxCAn0cBYFcr1dQrsmFAyADvDG9QCVt5IDPFtsT_0uTjia-vj9du8M_Nh7mkM")
		//.addHeader("User-Agent", "rodrigojosefagundes@gmail.com")
		//.build();

		//Response response = client.newCall(request).execute();
		
		//System.out.println(response.body().string());
		
		
		
		
		
		//1 - INSERE FRETE DE FRETE
		//
		//OkHttpClient client = new OkHttpClient();
		//
		//MediaType mediaType = MediaType.parse("application/json");
		//RequestBody body = RequestBody.create(mediaType, "{\"from\":{\"name\":\"teste\",\"phone\":\"77\",\"address\":\"iji\",\"city\":\"tijucas\",\"postal_code\":\"88200000\",\"document\":\"278.045.610-83\"},\"to\":{\"name\":\"oooo\",\"phone\":\"567576\",\"address\":\"joijoij\",\"city\":\"curitiba\",\"postal_code\":\"75830-112\"},\"options\":{\"receipt\":true,\"own_hand\":true,\"reverse\":true,\"non_commercial\":true,\"insurance_value\":\"63287678326786823767863286\"},\"service\":3,\"volumes\":[{\"height\":20,\"width\":20,\"length\":20,\"weight\":20}],\"agency\":49}");
		//Request request = new Request.Builder()
		//.url("https://sandbox.melhorenvio.com.br/api/v2/me/cart")
		//.post(body)
		//.addHeader("Accept", "application/json")
		//.addHeader("Content-Type", "application/json")
		//.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiIxYTNiZjY5MGFjYmUxZjczZTBhNzc1ZmYzNDM0NmM5YzAyYmFjNDY3MmM3NDBkNDkzMGYzYWM1MjNjMTUzZDEzMzQ0NDQyMzM3ZjFhNGIwYiIsImlhdCI6MTc0MDMzMTIyMC4wNDY3NzEsIm5iZiI6MTc0MDMzMTIyMC4wNDY3NzMsImV4cCI6MTc3MTg2NzIyMC4wMjQ4OTMsInN1YiI6IjllNDgwODhhLWRiNzMtNDkwZS04NDM1LTEyMWMzYzI5OGZmYiIsInNjb3BlcyI6WyJjYXJ0LXJlYWQiLCJjYXJ0LXdyaXRlIiwiY29tcGFuaWVzLXJlYWQiLCJjb21wYW5pZXMtd3JpdGUiLCJjb3Vwb25zLXJlYWQiLCJjb3Vwb25zLXdyaXRlIiwibm90aWZpY2F0aW9ucy1yZWFkIiwib3JkZXJzLXJlYWQiLCJwcm9kdWN0cy1yZWFkIiwicHJvZHVjdHMtZGVzdHJveSIsInByb2R1Y3RzLXdyaXRlIiwicHVyY2hhc2VzLXJlYWQiLCJzaGlwcGluZy1jYWxjdWxhdGUiLCJzaGlwcGluZy1jYW5jZWwiLCJzaGlwcGluZy1jaGVja291dCIsInNoaXBwaW5nLWNvbXBhbmllcyIsInNoaXBwaW5nLWdlbmVyYXRlIiwic2hpcHBpbmctcHJldmlldyIsInNoaXBwaW5nLXByaW50Iiwic2hpcHBpbmctc2hhcmUiLCJzaGlwcGluZy10cmFja2luZyIsImVjb21tZXJjZS1zaGlwcGluZyIsInRyYW5zYWN0aW9ucy1yZWFkIiwidXNlcnMtcmVhZCIsInVzZXJzLXdyaXRlIiwid2ViaG9va3MtcmVhZCIsIndlYmhvb2tzLXdyaXRlIiwid2ViaG9va3MtZGVsZXRlIiwidGRlYWxlci13ZWJob29rIl19.YzcVLme1YLCrDJdGQlG9kjGJwSgy8Ba42GlHTlzlaqHQ957gWpepw4M0burx3ejdrXThuUuMNycLFxrSqaTTiolJqN7XSnlegTLbJ_NGAzQn7QTxaG-o4shLFWtPXVfY6toIxwu0RzoXuRdg9ZFIIKPojwWj0O0TiXAOiI1VIGs-fV_wb5eLxm8bNpWMYGF6qcOdG3MJk3Gt5phkDcUlTjJegqrieJhps4ZW6a_EM3HGI6iNecaies4hVZjfqurGyXwLKK5VChNZLWaHcjhe0ReS0zDHod3zYw1a_zyqVgHnPIfIdOI2MYqDhXLP2w6OYEiAwnZiNaoiM8qdEgcFsSKZA1SlJnpB2fXEK0Tb1KZLJuI9BSKTMvrsAClUMS0QnvptRLEsvgkbiIYlutQNiWIFc_6MIS9YWyn7mgc9ZAKXnlonCyWzMg4yp8RrrKr7Cb7SkU3vzYyuDCjhJfGtf4DtsS5q9sMhYTurJJZ9T1y1_8Hpktz3bZ2AGbhYiDr4FHkE_QhFFr_REl0Y_w5cfXNSVNCENz8aXmKBIL6N0IQI3M622KB4T2FFe9xasejLQlvPeAvNpr1V-GWIWvQDZwEl7I6N9LB3dC9-Hdu6hPn4W0mxCAn0cBYFcr1dQrsmFAyADvDG9QCVt5IDPFtsT_0uTjia-vj9du8M_Nh7mkM")
		//.addHeader("User-Agent", "rodrigojosefagundes@gmail.com")
		//.build();
		//
		//Response response = client.newCall(request).execute();
		//
		//System.out.println(response.body().string());

		
		//######################
		
		//2 - FAZ A COMPRA DO FRETE PARA A ETIQUETA
		//
		//OkHttpClient client = new OkHttpClient();
		//
		//MediaType mediaType = MediaType.parse("application/json");
		//RequestBody body = RequestBody.create(mediaType, "{\"orders\":[\"9e55f440-38c6-4491-8884-18dae893ae58\"]}");
		//Request request = new Request.Builder()
		//.url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/checkout")
		//.post(body)
		//.addHeader("Accept", "application/json")
		//.addHeader("Content-Type", "application/json")
		//.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiI5NTYiLCJqdGkiOiIxYTNiZjY5MGFjYmUxZjczZTBhNzc1ZmYzNDM0NmM5YzAyYmFjNDY3MmM3NDBkNDkzMGYzYWM1MjNjMTUzZDEzMzQ0NDQyMzM3ZjFhNGIwYiIsImlhdCI6MTc0MDMzMTIyMC4wNDY3NzEsIm5iZiI6MTc0MDMzMTIyMC4wNDY3NzMsImV4cCI6MTc3MTg2NzIyMC4wMjQ4OTMsInN1YiI6IjllNDgwODhhLWRiNzMtNDkwZS04NDM1LTEyMWMzYzI5OGZmYiIsInNjb3BlcyI6WyJjYXJ0LXJlYWQiLCJjYXJ0LXdyaXRlIiwiY29tcGFuaWVzLXJlYWQiLCJjb21wYW5pZXMtd3JpdGUiLCJjb3Vwb25zLXJlYWQiLCJjb3Vwb25zLXdyaXRlIiwibm90aWZpY2F0aW9ucy1yZWFkIiwib3JkZXJzLXJlYWQiLCJwcm9kdWN0cy1yZWFkIiwicHJvZHVjdHMtZGVzdHJveSIsInByb2R1Y3RzLXdyaXRlIiwicHVyY2hhc2VzLXJlYWQiLCJzaGlwcGluZy1jYWxjdWxhdGUiLCJzaGlwcGluZy1jYW5jZWwiLCJzaGlwcGluZy1jaGVja291dCIsInNoaXBwaW5nLWNvbXBhbmllcyIsInNoaXBwaW5nLWdlbmVyYXRlIiwic2hpcHBpbmctcHJldmlldyIsInNoaXBwaW5nLXByaW50Iiwic2hpcHBpbmctc2hhcmUiLCJzaGlwcGluZy10cmFja2luZyIsImVjb21tZXJjZS1zaGlwcGluZyIsInRyYW5zYWN0aW9ucy1yZWFkIiwidXNlcnMtcmVhZCIsInVzZXJzLXdyaXRlIiwid2ViaG9va3MtcmVhZCIsIndlYmhvb2tzLXdyaXRlIiwid2ViaG9va3MtZGVsZXRlIiwidGRlYWxlci13ZWJob29rIl19.YzcVLme1YLCrDJdGQlG9kjGJwSgy8Ba42GlHTlzlaqHQ957gWpepw4M0burx3ejdrXThuUuMNycLFxrSqaTTiolJqN7XSnlegTLbJ_NGAzQn7QTxaG-o4shLFWtPXVfY6toIxwu0RzoXuRdg9ZFIIKPojwWj0O0TiXAOiI1VIGs-fV_wb5eLxm8bNpWMYGF6qcOdG3MJk3Gt5phkDcUlTjJegqrieJhps4ZW6a_EM3HGI6iNecaies4hVZjfqurGyXwLKK5VChNZLWaHcjhe0ReS0zDHod3zYw1a_zyqVgHnPIfIdOI2MYqDhXLP2w6OYEiAwnZiNaoiM8qdEgcFsSKZA1SlJnpB2fXEK0Tb1KZLJuI9BSKTMvrsAClUMS0QnvptRLEsvgkbiIYlutQNiWIFc_6MIS9YWyn7mgc9ZAKXnlonCyWzMg4yp8RrrKr7Cb7SkU3vzYyuDCjhJfGtf4DtsS5q9sMhYTurJJZ9T1y1_8Hpktz3bZ2AGbhYiDr4FHkE_QhFFr_REl0Y_w5cfXNSVNCENz8aXmKBIL6N0IQI3M622KB4T2FFe9xasejLQlvPeAvNpr1V-GWIWvQDZwEl7I6N9LB3dC9-Hdu6hPn4W0mxCAn0cBYFcr1dQrsmFAyADvDG9QCVt5IDPFtsT_0uTjia-vj9du8M_Nh7mkM")
		//.addHeader("User-Agent", "rodrigojosefagundes@gmail.com")
		//.build();
		//
		//Response response = client.newCall(request).execute();
		//   
		//System.out.println(response.body().string());
		
		
		
		
	}
}
