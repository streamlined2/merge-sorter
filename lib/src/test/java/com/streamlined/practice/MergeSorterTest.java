package com.streamlined.practice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MergeSorterTest {

	@Test
	@DisplayName("testing sorting in ascending order")
	void testSortingInAscendingOrder() {

		final int start = 0;
		final int finish = 10_000;
		var sampleList = IntStream.range(start, finish).mapToObj(Integer::valueOf).toList();

		var shuffledList = new ArrayList<>(sampleList);
		Collections.shuffle(shuffledList);
		var sortedList = List
				.of(new MergeSorter<Integer>(Comparator.naturalOrder(), shuffledList.toArray(new Integer[0])).sort());

		assertEquals(sampleList, sortedList);

	}

	@Test
	@DisplayName("testing sorting in descending order")
	void testSortingInDescendingOrder() {

		final int start = 0;
		final int finish = 10_000;
		var sampleList = IntStream.range(start, finish).map(a -> finish - a).mapToObj(Integer::valueOf).toList();

		var shuffledList = new ArrayList<>(sampleList);
		Collections.shuffle(shuffledList);
		var sortedList = List
				.of(new MergeSorter<Integer>(Comparator.reverseOrder(), shuffledList.toArray(new Integer[0])).sort());

		assertEquals(sampleList, sortedList);

	}

}
