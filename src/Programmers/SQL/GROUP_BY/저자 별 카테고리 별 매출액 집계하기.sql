select a.author_id, a.author_name, b.category, sum(b.price * s.sales) total_sales
from author a join book b on a.author_id = b.author_id
              join book_sales s on b.book_id = s.book_id
where date_format(s.sales_date, "%Y%m") = '202201'
group by a.author_name, b.category
order by a.author_id, b.category desc