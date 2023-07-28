SELECT a.apnt_no, p.pt_name, p.pt_no, d.mcdp_cd, d.dr_name, a.apnt_ymd
from patient p  join appointment a
                     on p.pt_no = a.pt_no
                join doctor d
                     on a.mddr_id = d.dr_id
where a.mcdp_cd = 'CS' and a.apnt_ymd like '2022-04-13%'
  and a.apnt_cncl_yn = 'N'
order by a.apnt_ymd



--  date_format(a.apnt_ymd, '%Y-%m-%d %H:%i:%s.%f')