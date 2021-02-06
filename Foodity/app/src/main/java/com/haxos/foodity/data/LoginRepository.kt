package com.haxos.foodity.data

import com.haxos.foodity.LoginCallback
import com.haxos.foodity.R
import com.haxos.foodity.UserService
import com.haxos.foodity.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(
        private val userService : UserService
) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {

        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
//        dataSource.logout()
    }

    fun login(username: String, password: String, loginCallback: LoginCallback) {
        /*public void submit() {
                    final String userName = ((TextView) findViewById(R.id.accountName)).getText().toString();
                    final String userPass = ((TextView) findViewById(R.id.accountPassword)).getText().toString();
                    new AsyncTask<Void, Void, Intent>() {
                    @Override
                    protected Intent doInBackground(Void... params) {
                    String authtoken = sServerAuthenticate.userSignIn(userName, userPass, mAuthTokenType);
                    final Intent res = new Intent();
                    res.putExtra(AccountManager.KEY_ACCOUNT_NAME, userName);
                    res.putExtra(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE);
                    res.putExtra(AccountManager.KEY_AUTHTOKEN, authtoken);
                    res.putExtra(PARAM_USER_PASS, userPass);
                    return res;
                }
                    @Override
                    protected void onPostExecute(Intent intent) {
                        finishLogin(intent);
                    }
                }.execute();
                }*/

        userService.getToken(username, password, "USER_ACCESS")
                .equeue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.body() != null){
                            loginCallback.onSuccess(User("0", "TEST", "TEST", "TEST"))
                        } else {
                            loginCallback.onError(R.string.login_failed)
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        loginCallback.onError(R.string.login_failed)
                    }
                }

        /*userService.getUsersByUsername(username)
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.body()?.password == password) {
                            loginCallback.onSuccess(response.body()!!)
                        } else {
                            loginCallback.onError(R.string.login_failed)
                        }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        loginCallback.onError(R.string.login_failed)
                    }
                })*/
        // handle login
//        val result = dataSource.login(username, password)

        /*if (result is Result.Success) {
            setLoggedInUser(result.data)
        }*/
    }

    private fun setLoggedInUser(user: User) {
        this.user = user
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
