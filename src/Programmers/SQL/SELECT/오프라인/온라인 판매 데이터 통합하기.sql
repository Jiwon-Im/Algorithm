select sales_date, product_id, user_id, sum(sales_amount) sales_amount
from (SELECT date_format(sales_date, "%Y-%m-%d") sales_date, product_id, user_id, sales_amount
      from online_sale
      where year (sales_date)=2022 and month (sales_date)=3
      union
      SELECT date_format(sales_date, "%Y-%m-%d") sales_date, product_id, null, sales_amount
      from offline_sale
      where year (sales_date)=2022 and month (sales_date)=3) a
group by sales_date, product_id, user_id
order by sales_date, product_id, user_id
