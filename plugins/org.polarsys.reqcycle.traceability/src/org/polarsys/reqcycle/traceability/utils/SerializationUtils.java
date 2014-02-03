package org.polarsys.reqcycle.traceability.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.polarsys.reqcycle.traceability.model.TType;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;

import static com.google.common.collect.Iterables.transform;

public class SerializationUtils {

	/**
	 * Returns a serialized version of the given {@link TType}
	 * 
	 * @param object
	 * @return null if an error occurs
	 */
	public static String serialize(Object object) {
		ObjectOutputStream obOS = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			obOS = new ObjectOutputStream(byteArrayOutputStream);
			obOS.writeObject(object);
			obOS.flush();
			String result = getByteRepresentation(byteArrayOutputStream
					.toByteArray());
			return result;
			// return getByteRepresentation(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (obOS != null) {
				try {
					obOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Return the {@link TType} serialized in the given string
	 * 
	 * @param getByteRepresentation
	 *            (inputString)
	 * @return null if an error occurs
	 * 
	 */
	public static <T> T deserialize(String inputString) {
		ObjectInputStream stream = null;
		byte[] inputBytes = getRealRepresentation(inputString);
		try {
			stream = new ObjectInputStream(new ByteArrayInputStream(inputBytes));
			T result = (T) stream.readObject();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static byte[] getRealRepresentation(String inputString) {
		Iterable<Byte> allBytes = transform(
				Splitter.on(',').split(inputString),
				new Function<String, Byte>() {

					@Override
					public Byte apply(String arg0) {
						return Byte.valueOf(arg0);
					}

				});
		return com.google.common.primitives.Bytes.toArray(Lists
				.newArrayList(allBytes));
	}

	private static String getByteRepresentation(byte[] bytes) {
		Iterable<Byte> bytesIt = Bytes.asList(bytes);
		String joinedString = Joiner.on(',').join(
				transform(bytesIt, Functions.toStringFunction()));
		return joinedString;
	}
}
