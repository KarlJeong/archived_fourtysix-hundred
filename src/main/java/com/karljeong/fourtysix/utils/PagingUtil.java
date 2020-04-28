package com.karljeong.fourtysix.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PagingUtil {
    final static int pageShowingNumber = 5;

    public static List<Integer> getPageList(int totalPages, int currentPage) {
        if (totalPages <= pageShowingNumber) {
            IntStream.rangeClosed(1,  totalPages).boxed().collect(Collectors.toList());
        } else if ( totalPages > pageShowingNumber && totalPages - currentPage >= 2 ) {
            IntStream.rangeClosed(currentPage - 2,  currentPage + 2).boxed().collect(Collectors.toList());
        } else if ( totalPages > pageShowingNumber && totalPages - currentPage < 2 ) {
            IntStream.rangeClosed(currentPage - 2,  currentPage).boxed().collect(Collectors.toList());
        }


        return IntStream.rangeClosed(1,  totalPages).boxed().collect(Collectors.toList());
    }
}
