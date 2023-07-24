
SELECT year(s.sales_date) year, month(s.sales_date) month, gender, count(distinct u.user_id)  users
from user_info u inner join online_sale s
on u.user_id = s.user_id
where u.gender is not null
group by year(s.sales_date), month(s.sales_date), gender
order by year, month, gender