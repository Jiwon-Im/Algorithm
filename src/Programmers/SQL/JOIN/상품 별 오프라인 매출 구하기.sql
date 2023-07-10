SELECT p.product_code, sum(p.price*s.sales_amount) sales
from product p right join offline_sale s
                          on p.product_id = s.product_id
group by product_code
order by sales desc, product_code