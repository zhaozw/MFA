package com.example.mfa.networking;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mfa.R;

public class LoginFunctionsDisplay {

	public static void loginChoice(final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.LoginOrRegister));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);
		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.login_choice_dialog,
				frameView);

		Button login = (Button) dialoglayout
				.findViewById(R.id.LoginDialog_Login);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				LoginFunctionsDisplay.login(context);
				alertDialog.dismiss();
			}
		});

		Button register = (Button) dialoglayout
				.findViewById(R.id.LoginDialog_Register);
		register.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				LoginFunctionsDisplay.register(context);
				alertDialog.dismiss();
			}
		});

		Button cancel = (Button) dialoglayout
				.findViewById(R.id.Login_dialog_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.show();

	}

	public static void login(final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.Login));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);
		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.login_dialog, frameView);

		final EditText emailEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.EmailEnterTextBox);
		final EditText passwordEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.PasswordEnterTextBox);

		Button login = (Button) dialoglayout.findViewById(R.id.LoginButton);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast;
				Log.d("LoginActivity", emailEditTextBox.getText().toString()
						+ "  " + passwordEditTextBox.getText().toString());
				if (emailEditTextBox.getText().toString().length() > 0
						&& passwordEditTextBox.getText().toString().length() > 0) {
					switch (LoginFunctions.attemptLogin(context,
							emailEditTextBox.getText().toString(),
							passwordEditTextBox.getText().toString())) {
					case (1):
						toast = Toast.makeText(context,
								context.getString(R.string.LoginSuccessfull),
								Toast.LENGTH_LONG);
						toast.show();
						alertDialog.dismiss();
						break;

					case (2):
						toast = Toast.makeText(context, context
								.getString(R.string.IncorrectUsernamePassword),
								Toast.LENGTH_SHORT);
						toast.show();
						break;

					case (3):
						toast = Toast.makeText(context,
								context.getString(R.string.ConnectionError),
								Toast.LENGTH_SHORT);
						toast.show();
						break;

					case (4):
						toast = Toast.makeText(context,
								context.getString(R.string.LoginFailed),
								Toast.LENGTH_SHORT);
						toast.show();
						break;

					}
				} else {
					toast = Toast.makeText(context,
							context.getString(R.string.EnterTextNextTime),
							Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		Button cancel = (Button) dialoglayout
				.findViewById(R.id.CancelLoginButton);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.show();

	}

	public static void register(final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(context.getString(R.string.Register));
		final FrameLayout frameView = new FrameLayout(context);
		builder.setView(frameView);
		final AlertDialog alertDialog = builder.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View dialoglayout = inflater.inflate(R.layout.register_dialog,
				frameView);

		final EditText emailEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.emailEditTextRegister);
		final EditText passwordEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.passwordEditTextRegister);
		final EditText confirmPasswordEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.confirmPasswordEditTextRegister);
		final EditText usernameEditTextBox = (EditText) dialoglayout
				.findViewById(R.id.userNameEditTextRegister);

		Button login = (Button) dialoglayout
				.findViewById(R.id.RegisterDialogRegister);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast toast;
				Log.d("LoginActivity", usernameEditTextBox.getText().toString()
						+ "  " + emailEditTextBox.getText().toString() + "  "
						+ "" + passwordEditTextBox.getText().toString() + " "
						+ confirmPasswordEditTextBox.getText().toString());

				if (passwordEditTextBox
						.getText()
						.toString()
						.compareTo(
								confirmPasswordEditTextBox.getText().toString()) == 0) {
					if (emailEditTextBox.getText().toString().length() > 0
							&& passwordEditTextBox.getText().toString()
									.length() > 0
							&& usernameEditTextBox.getText().toString()
									.length() > 0) {
						switch (LoginFunctions.registerAccount(context,
								usernameEditTextBox.getText().toString(),
								emailEditTextBox.getText().toString(),
								passwordEditTextBox.getText().toString())) {
						case (1):
							toast = Toast.makeText(
									context,
									context.getString(R.string.RegistrationSuccesfull),
									Toast.LENGTH_LONG);
							toast.show();
							alertDialog.dismiss();
							break;
						case (2):

							break;
						case (3):

							break;

						default:

						}
					} else {
						toast = Toast.makeText(context,
								context.getString(R.string.EnterTextNextTime),
								Toast.LENGTH_LONG);
						toast.show();
					}

				} else {
					toast = Toast.makeText(context,
							context.getString(R.string.PasswordsDontMatch),
							Toast.LENGTH_LONG);
					toast.show();
					passwordEditTextBox.setText("");
					confirmPasswordEditTextBox.setText("");
				}
			}
		});

		Button cancel = (Button) dialoglayout
				.findViewById(R.id.RegisterDialogCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});

		alertDialog.show();

	}

}
