SELECT pt_name, pt_no, gend_cd, age, ifnull(tlno, 'NONE') tlno
from patient
where age <13 and gend_cd = 'W'
order by age desc, pt_name