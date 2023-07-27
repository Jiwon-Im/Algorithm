SELECT r.rest_id, rest_name, food_type, favorites, address, round(avg(review_score),2) score
from rest_review r inner join rest_info i
                              on r.rest_id = i.rest_id
where address like '서울%'
group by r.rest_id
order by score desc, favorites desc