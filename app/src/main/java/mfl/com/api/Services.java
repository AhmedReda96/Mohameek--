package mfl.com.api;

import io.reactivex.Observable;
import io.reactivex.Single;
import mfl.com.pojo.signin.SignInRequest;
import mfl.com.pojo.signup.SignUpRequest;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {

    @POST("auth/register")
    @FormUrlEncoded
    Single<SignUpRequest> getSignUpRequest(
            @Field("firstname") String firstName,
            @Field("lastname") String lastName,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("card_number") String cardNumber,
            @Field("photo") String photo);


    @POST("auth/login")
    @FormUrlEncoded
    Single<SignInRequest> getSignInRequest(
            @Field("card_number") String card_number,
            @Field("password") String password);
}
