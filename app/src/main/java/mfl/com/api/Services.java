package mfl.com.api;

import io.reactivex.Observable;
import io.reactivex.Single;
import mfl.com.pojo.accountInfo.get.GetProfileResponse;
import mfl.com.pojo.accountInfo.put.EditAccountInfoResponse;
import mfl.com.pojo.accountInfo.put.EditAccountInfoRequest;
import mfl.com.pojo.changePassword.ChangePasswordResponse;
import mfl.com.pojo.news.NewNewsResponse;
import mfl.com.pojo.signin.SignInResponse;
import mfl.com.pojo.signup.SignUpResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Services {

    @POST("auth/register")
    @FormUrlEncoded
    Single<SignUpResponse> getSignUpRequest(
            @Field("firstname") String firstName,
            @Field("lastname") String lastName,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("card_number") String cardNumber,
            @Field("photo") String photo);


    @POST("auth/login")
    @FormUrlEncoded
    Single<SignInResponse> getSignInRequest(
            @Field("card_number") String card_number,
            @Field("password") String password);

    @GET("news")
    Observable<NewNewsResponse> getNewsRequestTest(@Query("page") int page,@Query("token") String token);


    @POST("auth/password/reset")
    @FormUrlEncoded
    Single<ChangePasswordResponse> getChangePasswordRequest(
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("token") String token);


    @PUT("lawyer/profile/edit")
    Single<EditAccountInfoResponse> getAccountInfoRequest(@Body EditAccountInfoRequest editAccountInfoRequest);

    @GET("lawyer/profile")
    Single<GetProfileResponse> getProfileResponse(@Query("token") String token);

}
