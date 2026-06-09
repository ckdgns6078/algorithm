SELECT 
    C.ITEM_ID, 
    C.ITEM_NAME, 
    C.RARITY
FROM 
    ITEM_INFO AS C                 -- 1. 자식 아이템 정보 테이블
JOIN 
    ITEM_TREE AS T 
ON 
    C.ITEM_ID = T.ITEM_ID          -- 2. 자식 아이템과 트리를 연결
JOIN 
    ITEM_INFO AS P                 -- 3. 부모 아이템 정보 테이블을 한 번 더 연결
ON 
    T.PARENT_ITEM_ID = P.ITEM_ID   -- 4. 트리의 부모 ID와 부모 테이블을 연결
WHERE 
    P.RARITY = 'RARE'              -- 5. 부모의 희귀도가 'RARE'인 것만 필터링
ORDER BY 
    C.ITEM_ID DESC;