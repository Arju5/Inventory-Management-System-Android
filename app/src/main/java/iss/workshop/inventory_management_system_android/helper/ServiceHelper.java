package iss.workshop.inventory_management_system_android.helper;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import iss.workshop.inventory_management_system_android.model.Department;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.model.Supplier;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionSummaryViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.StationeryRequisitionProductViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.StationeryRetrievalSummaryViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.StationeryRetrievalViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class ServiceHelper {

    private static ApiService apiService;
    private static Cache cache;
    //private static String BASE_URL = "http://shweyamoneoo-001-site1.btempurl.com/api/";
    private static String BASE_URL = "http://10.0.2.2:62068/api/";
    //private static String BASE_URL = " http:www.dnndev.me/api/";
    ///http://www.dnndev.me/

    public static ApiService getClient(final Context context) {
        if (apiService == null) {

            Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    int maxAge = 300; // read from cache for 5 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                }
            };

            //setup cache
            File httpCacheDirectory = new File(context.getCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            cache = new Cache(httpCacheDirectory, cacheSize);

            final OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
            okClientBuilder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            okClientBuilder.cache(cache);

            OkHttpClient okClient = okClientBuilder.build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = client.create(ApiService.class);
        }
        return apiService;
    }

    public static void removeFromCache(String url) {
        try {
            Iterator<String> it = cache.urls();
            while (it.hasNext()) {
                String next = it.next();
                if (next.contains(BASE_URL + url)) {
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public interface ApiService {
        // Product APIS starts
        @GET("Product/GetProductList")
        Call<ArrayList<Product>> getProductList();

        // Product APIs ends

        //Employee APIS starts

        @GET("Employee/GetEmpObj")//For Login
        Call<Employee> getEmpObj(@Query("Username") String Username, @Query("Password") String Password);

        @GET("Employee/GetEmployeeById")
        Call<Employee> getEmployeeById(@Query("empId") int empId);

        @GET("Employee/GetEmployeeList")
        Call<ArrayList<Employee>> getEmployeeList();

        //Employee APIS ends

        //Requisition APIs start
        @GET("Requisition/GetRequisitionById")
        Call<RequisitionForm> getReqByid(@Query("reqId") Integer reqId);

        @GET("Requisition/GetReqSummary")
        Call<RequisitionSummaryViewModel> getReqSummary(@Query("Username") String Username);

        @GET("Requisition/ViewRequisitionFormById")
        Call<RequisitionViewModel> ViewRFById(@Query("reqId") Integer reqId, @Query("Username") String Username);

        @GET("Requisition/ViewApprovalRF")
        Call<RequisitionViewModel> ViewApprovalRF(@Query("id") Integer reqId);

        @GET("Requisition/ApplyRequisitionForm")
        Call<RequisitionViewModel> ApplyRF();

        @POST("Requisition/SaveRf")
        Call<RequisitionViewModel> SaveRf(@Body RequisitionViewModel requisitionViewModel);

        @POST("Requisition/Approve")
        Call<RequisitionViewModel> Approve(@Body RequisitionViewModel requisitionViewModel);
        @POST("Requisition/Reject")
        Call<RequisitionViewModel> Reject(@Body RequisitionViewModel requisitionViewModel);

        @POST("Requisition/Cancel")
        Call<String> Cancel(@Query("id") Integer reqId);
        //Requisition APIs ends

        //Dashboard APIS starts

        /*@GET("Dashboard/GetDashBox")//For Dashboard
        Call<DashboardViewModel> getDash(@Query("Username") String Username);

        @GET("Dashboard/GetDashBox")//For Dashboard
        Call<Object> getDashTest(@Query("Username") String Username);*/
        //Dash APIS ends

        // Disbursement APIS starts
        @GET("Disbursement/GetCreatedDisbursementList")
//For Disbursement Lists
        Call<ArrayList<DisbursementForm>> getCreatedDisbursementList();

        @GET("Disbursement/GetPendingAssignmentDisbursementList")
//For Disbursement Lists
        Call<ArrayList<DisbursementForm>> getPendingAssignmentDisbursementList();

        @GET("Disbursement/GetPendingDeliveryDisbursementList")
//For Disbursement Lists
        Call<ArrayList<DisbursementForm>> getPendingDeliveryDisbursementList();

        @GET("Disbursement/GetCompleteDisbursementList")
//For Disbursement Lists
        Call<ArrayList<DisbursementForm>> getCompleteDisbursementList();

        @GET("Disbursement/GetDisbursementFormFullDetails")
//For Disbursement Lists
        Call<DisbursementViewModel> getDisbursementFormFullDetailsByDFCode(@Query("dfCode") String dfCode);

        @GET("Disbursement/GetAssignedSRRForms")
            //SRRF with complete status to create Disbursement form
        Call<ArrayList<StationeryRetrievalRequisitionForm>> getSRRFAssignedFormsToCreateDisbursement();

        @POST("Disbursement/SaveDF")
        Call<DisbursementViewModel> SaveDF(@Body DisbursementViewModel dfViewModel);

        @POST("Disbursement/DeliverDF")
        Call<DisbursementViewModel> DeliverDF(@Body DisbursementViewModel dfViewModel);

        @POST("Disbursement/ApproveDF")
        Call<DisbursementViewModel> approveDFByDeptRep(@Body DisbursementViewModel dfViewModel);

        @POST("Disbursement/CeatedDF")
        Call<List<DisbursementForm>> getCreatedDFByDepRep(@Query("id") Integer id);

        // Disbursement APIS ends

        // Stationery APIS starts
        @GET("Stationery/GetOpenSRSummary")
        Call<StationeryRetrievalSummaryViewModel> getOpenSRSummary();

        @GET("Stationery/GetPendingSRSummary")
        Call<StationeryRetrievalSummaryViewModel> getPendingSRSummary();

        @GET("Stationery/GetCompletedSRSummary")
        Call<StationeryRetrievalSummaryViewModel> getCompletedSRSummary();

        @GET("Stationery/GetPARequistionForm")
        Call<ArrayList<RequisitionForm>> getRequisitionList();

        @POST("Stationery/PostProductsBySelectedRequisition")
        Call<List<StationeryRetrievalProduct>> postProductsBySelectedRequisition(@Body List<Integer> selectedRequisition);

        @POST("Stationery/CreateSRForm")
        Call<StationeryRequisitionProductViewModel> createSRForm(@Body StationeryRequisitionProductViewModel srvm);

        @GET("Stationery/GetOpenSRFormById")
        Call<StationeryRetrievalViewModel> getSRFormBySelectedId(@Query("SFId") Integer SFId);

        @GET("Stationery/GetPendingSRFormById")
        Call<StationeryRetrievalViewModel> getSRPendingFormBySelectedId(@Query("SFId") Integer SFId);

        @POST("Stationery/SaveReceivedQtyForOpenSRF")
        Call<StationeryRetrievalViewModel> saveReceivedQtyForOpenSRF(@Body StationeryRetrievalViewModel srvm);

        @POST("Stationery/SaveAssignedProductsInPendingSR")
        Call<StationeryRetrievalViewModel> saveAssignedProductsInPendingSR(@Query("srId") Integer srId,@Body StationeryRetrievalViewModel srvm);


        @GET("Stationery/GetCompleteSRById")
        Call<StationeryRetrievalViewModel> getCompletedSRFormBySelectedId(@Query("SFId") Integer SFId);
        // Stationery APIS ends
        //Department APIs start
        @GET("Department/GetDepartmentList")
        Call<ArrayList<Department>> getDepartmentList();

        @GET("Department/GetDepartmentInfo")
        Call<Department> getDepartmentInfo(@Query("deptName") String deptName);
        //Department APIs end

        //Supplier APIs start
        @GET("Supplier/GetSupplierList")
        Call<ArrayList<Supplier>> getSupplierList();
        //Supplier APIs end

    }

}
