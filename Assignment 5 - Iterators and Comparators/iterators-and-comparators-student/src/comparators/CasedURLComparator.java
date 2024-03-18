/*
 * Copyright 2023 Marc Liberatore.
 */

 package comparators;

import java.util.Comparator;

/**
 * A comparator to determine the order of two web pages. The two web pages are
 * compared lexicographically. However, if the CasedURLComparator is created
 * with ignoreCase set to true, then the comparison should be case-insensitive.
 */
public class CasedURLComparator implements Comparator<WebPageRecord> {
    final boolean ignoreCase;

    public CasedURLComparator(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public int compare(WebPageRecord x, WebPageRecord y) {
        if (ignoreCase)
        {
            String low1 = x.URL.toLowerCase();
            String low2 = y.URL.toLowerCase();
            if (low1.compareTo(low2) > 0)
            {
                return 1;
            }
            else if (low1.compareTo(low2) < 0)
            {
                return -1;
            }
        }
        else
        {
            if (x.URL.compareTo(y.URL) > 0)
            {
                return 1;
            }
            else if (x.URL.compareTo(y.URL) < 0)
            {
                return -1;
            }
        }
        return 0;
    }

}
