SELECT u.user_id,
       u.nickname,
       concat(u.city, ' ', u.street_address1, ' ', u.street_address2)            as 전체주소,
       concat(substr(tlno, 1, 3), '-', substr(tlno, 4, 4), '-', substr(tlno, 8)) as 전화번호
from used_goods_board b,
     used_goods_user u
where b.writer_id = u.user_id
group by u.user_id
having count(*) > 2
order by user_id desc