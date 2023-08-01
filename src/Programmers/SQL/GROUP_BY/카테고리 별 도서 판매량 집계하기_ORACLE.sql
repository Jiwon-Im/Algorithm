-- EXTRACT
-- SELECT b.category, sum(sales) total_sales
-- from book b join book_sales s
-- on b.book_id = s.book_id
-- where EXTRACT(YEAR FROM SALES_DATE) = 2022 AND EXTRACT(MONTH FROM SALES_DATE) = 01
-- group by b.category
-- order by b.category

-- to_char
SELECT b.category, sum(sales) total_sales
from book b join book_sales s
                 on b.book_id = s.book_id
where to_char(SALES_DATE, 'YYYYMM') = 202201
group by b.category
order by b.category