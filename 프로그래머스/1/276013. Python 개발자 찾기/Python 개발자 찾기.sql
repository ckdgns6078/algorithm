-- 코드를 작성해주세요
select 
    ID , EMAIL , FIRST_NAME , LAST_NAME
from 
DEVELOPER_INFOS 
where 
    SKILL_1 in ('Python') or skill_2 in ('Python') or skill_3 in ('Python')
    order by id asc;