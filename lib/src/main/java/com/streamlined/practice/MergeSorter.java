package com.streamlined.practice;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSorter<T> {

	private final T[] data;
	private final Comparator<T> comparator;

	public MergeSorter(Comparator<T> comparator, T... data) {
		this.comparator = comparator;
		this.data = data;
	}

	public T[] sort() {
		T[] mergedData = (T[]) new Object[data.length];
		for (int chunkSize = 1; chunkSize < data.length; chunkSize *= 2) {
			final int chunkPairSize = chunkSize * 2;
			for (int firstChunkStart = 0,
					secondChunkStart = chunkSize; firstChunkStart < data.length; firstChunkStart += chunkPairSize, secondChunkStart += chunkPairSize) {
				mergeChunks(mergedData, firstChunkStart, secondChunkStart, chunkSize);
				copyMergedData(mergedData, firstChunkStart, chunkPairSize);
			}
		}
		return Arrays.copyOf(data, data.length);
	}

	private void copyMergedData(T[] mergedData, int startIndex, int size) {
		System.arraycopy(mergedData, 0, data, startIndex, Math.min(size, data.length - startIndex));
	}

	private void mergeChunks(T[] mergedData, int firstChunkStart, int secondChunkStart, int chunkSize) {
		final int firstChunkEnd = Math.min(firstChunkStart + chunkSize, data.length);
		final int secondChunkEnd = Math.min(secondChunkStart + chunkSize, data.length);

		int mergedIndex = 0;
		while (firstChunkStart < firstChunkEnd && secondChunkStart < secondChunkEnd) {

			while (firstChunkStart < firstChunkEnd
					&& comparator.compare(data[firstChunkStart], data[secondChunkStart]) < 0) {
				mergedData[mergedIndex++] = data[firstChunkStart++];
			}

			if (firstChunkStart < firstChunkEnd) {
				while (secondChunkStart < secondChunkEnd
						&& comparator.compare(data[firstChunkStart], data[secondChunkStart]) >= 0) {
					mergedData[mergedIndex++] = data[secondChunkStart++];
				}
			}

		}

		while (firstChunkStart < firstChunkEnd) {
			mergedData[mergedIndex++] = data[firstChunkStart++];
		}

		while (secondChunkStart < secondChunkEnd) {
			mergedData[mergedIndex++] = data[secondChunkStart++];
		}

	}

}
