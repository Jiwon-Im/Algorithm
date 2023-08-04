select p.member_name, r.review_text, date_format(r.review_date, '%Y-%m-%d') review_date
from member_profile p join rest_review r
                           on p.member_id = r.member_id
where p.member_id in (select member_id from rest_review group by member_id having count(*) = (select max(score) from (SELECT count(*) score
                                                                                                                      from rest_review r
                                                                                                                      group by member_id) a))
order by r.review_date, r.review_text
