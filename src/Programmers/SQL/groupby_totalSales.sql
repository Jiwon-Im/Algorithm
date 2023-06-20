SELECT u.user_id, u.nickname, sum(b.price) total_sales
from used_goods_board b,
     used_goods_user u
where b.writer_id = u.user_id
  and b.status = 'done'
group by user_id
having total_sales >= 700000
order by total_sales