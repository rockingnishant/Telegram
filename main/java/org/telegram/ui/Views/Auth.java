/*
 * Copyright (c) 2013 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.telegram.ui.Views;

import java.io.IOException;

import com.google.android.gms.common.Scopes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTubeScopes;

public class Auth {
	// Register an API key here: https://code.google.com/apis/console
	public static final String KEY = "AIzaSyCSOiAwXDVnZulf23_OOF7hLVTYKABBaWs";

	public static final String[] SCOPES = { Scopes.PLUS_ME,
			YouTubeScopes.YOUTUBE };
	public static final HttpTransport transport = AndroidHttp
			.newCompatibleTransport();
	public static final JsonFactory jsonFactory = new GsonFactory();
}
