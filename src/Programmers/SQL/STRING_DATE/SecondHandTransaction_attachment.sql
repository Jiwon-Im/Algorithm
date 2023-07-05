SELECT concat('/home/grep/src/', b.board_id, '/', f.file_id, file_name, file_ext) as file_path
from used_goods_board b, used_goods_file f
where b.board_id = f.board_id
  and b.views = (select max(b.views) from used_goods_board b)
order by f.file_id desc