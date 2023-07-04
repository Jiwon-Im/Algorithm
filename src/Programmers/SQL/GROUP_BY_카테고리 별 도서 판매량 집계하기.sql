SELECT CATEGORY, SUM(S.SALES) TOTAL_SALES
FROM BOOK B RIGHT JOIN BOOK_SALES S
                       ON B.BOOK_ID = S.BOOK_ID
WHERE YEAR(S.SALES_DATE) = 2022 AND MONTH(S.SALES_DATE) = 1
GROUP BY CATEGORY
ORDER BY CATEGORY