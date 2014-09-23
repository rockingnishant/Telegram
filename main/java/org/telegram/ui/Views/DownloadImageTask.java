package org.telegram.ui.Views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.telegram.ui.Views.ChatActivityEnterView.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, String> {
	private static final String TAG = DownloadImageTask.class.getSimpleName();
	public async asyncobject;
	String path;

	public DownloadImageTask(String url) {
		path = url;

	}

	public DownloadImageTask(String mImageURl, async async) {
		// TODO Auto-generated constructor stub
		path = mImageURl;
		asyncobject = async;
	}

	ImageView imageView;

	@Override
	protected String doInBackground(String... imageViews) {
		// this.imageView = imageViews[0];
		String finalpath=download_Image(path);
		if(finalpath!=null)
		return finalpath;
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		if (result != null) {
			asyncobject.onImage(result);
		} else {
			Log.d("test", "asyncobject is null");
		}

	}

	private String download_Image(String url) {
		String finalpath = null;
		// Bitmap bm = null;
		Log.d("test", "inside async task download image");
		try {
			URL aURL = new URL(url);
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			Log.d("test", "inpiutstraem got");

			// URL url = new URL ("file://some/path/anImage.png");
			// InputStream input = url.openStream();
			try {
				// The sdcard directory e.g. '/sdcard' can be used directly, or
				// more safely abstracted with getExternalStorageDirectory()
				File storagePath = Environment.getExternalStorageDirectory();
				File newfile = new File(storagePath, "myImage.jpg");
				if (newfile.createNewFile()) {
					newfile.createNewFile();
					Log.d(TAG, " 7. create new file inside if" + newfile);
				}
				Log.d("test", "newfile" + newfile.getAbsolutePath());
				finalpath = newfile.getAbsolutePath();
				OutputStream output = new FileOutputStream(newfile);
				// Log.d("test", "path"+output);
				try {
					int aReasonableSize = 1024;
					byte[] buffer = new byte[aReasonableSize];
					int bytesRead = 0;
					while ((bytesRead = is.read(buffer, 0, buffer.length)) >= 0) {
						output.write(buffer, 0, bytesRead);
						Log.d("test", "insidewhile");
					}
				} catch (IOException e) {
					Log.e("test", " 1. Error getting the image from server : "
							+ e.getMessage().toString());
				} finally {
					output.close();
				}
			} catch (IOException e) {
				Log.e("test", "2. Error getting the image from server : "
						+ e.getMessage().toString());
			} finally {
				is.close();
			}

			// bm = BitmapFactory.decodeStream(is);
			// // BufferedInputStream bis = new BufferedInputStream(is);
			// // bm = decodeSampledBitmapFromResourceMemOpt(is,150,214);
			// bm = getResizedBitmap(bm, 214, 150);
			// bis.close();
			is.close();
		} catch (IOException e) {
			Log.e("test", "3 .Error getting the image from server : "
					+ e.getMessage().toString());
		}
		return finalpath;
	}

	// public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
	//
	// int width = bm.getWidth();
	//
	// int height = bm.getHeight();
	//
	// float scaleWidth = ((float) newWidth) / width;
	//
	// float scaleHeight = ((float) newHeight) / height;
	//
	// // CREATE A MATRIX FOR THE MANIPULATION
	//
	// Matrix matrix = new Matrix();
	//
	// // RESIZE THE BIT MAP
	//
	// matrix.postScale(scaleWidth, scaleHeight);
	//
	// // RECREATE THE NEW BITMAP
	//
	// Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
	// matrix, false);
	//
	// return resizedBitmap;
	//
	// }

	// public Bitmap decodeSampledBitmapFromResourceMemOpt(
	// InputStream inputStream, int reqWidth, int reqHeight) {
	//
	// byte[] byteArr = new byte[0];
	// byte[] buffer = new byte[1024];
	// int len;
	// int count = 0;
	//
	// try {
	// while ((len = inputStream.read(buffer)) > -1) {
	// if (len != 0) {
	// if (count + len > byteArr.length) {
	// byte[] newbuf = new byte[(count + len) * 2];
	// System.arraycopy(byteArr, 0, newbuf, 0, count);
	// byteArr = newbuf;
	// }
	//
	// System.arraycopy(buffer, 0, byteArr, count, len);
	// count += len;
	// }
	// }
	//
	// final BitmapFactory.Options options = new BitmapFactory.Options();
	// options.inJustDecodeBounds = true;
	// BitmapFactory.decodeByteArray(byteArr, 0, count, options);
	//
	// Log.d(TAG, "" + reqWidth + " and" + reqHeight);
	// options.inSampleSize = calculateInSampleSize(options, reqWidth,
	// reqHeight);
	// options.inPurgeable = true;
	// options.inInputShareable = true;
	// options.inJustDecodeBounds = false;
	// options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	//
	// int[] pids = { android.os.Process.myPid() };
	// // MemoryInfo myMemInfo = mAM.getProcessMemoryInfo(pids)[0];
	// // Log.e(TAG, "dalvikPss (decoding) = " + myMemInfo.dalvikPss);
	//
	// return BitmapFactory.decodeByteArray(byteArr, 0, count, options);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// return null;
	// }
	// }

	// public static int calculateInSampleSize(BitmapFactory.Options options,
	// int reqWidth, int reqHeight) {
	// // Raw height and width of image
	// final int height = options.outHeight;
	// final int width = options.outWidth;
	// int inSampleSize = 1;
	//
	// if (height > reqHeight || width > reqWidth) {
	//
	// // Calculate ratios of height and width to requested height and
	// // width
	// final int heightRatio = Math.round((float) height
	// / (float) reqHeight);
	// final int widthRatio = Math.round((float) width / (float) reqWidth);
	//
	// // Choose the smallest ratio as inSampleSize value, this will
	// // guarantee
	// // a final image with both dimensions larger than or equal to the
	// // requested height and width.
	// inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	// }
	//
	// Log.d(TAG, "insamplesize" + inSampleSize);
	// return inSampleSize;
	// }

}
