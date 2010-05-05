SELECT 
	C.CATEGORY AS "id",
	C.NAME AS "name",
	C.MODULE AS "module",
	C.HIDDEN AS "hidden",
	C.BUTTON_PICTURE AS "button_picture",
	C.DISABLE_REKLAMA AS "disable_reklama",
	C.ARTICLE_BASED AS "article_based",
	C.PICTURE_BASED AS "picture_based",
	C.IS_SPECIAL AS "is_special",
	C.ACTION AS "action",
	C.SHOW_TOP AS "show_top",
	C.DISABLE_MENU AS "disable_menu",
	C.DISABLE_POMOC AS "disable_pomoc",
	md5(C.ID) AS "hash_md5",
	(SELECT COUNT(category.id) AS subcategories_count FROM category, subcategory WHERE category.id = subcategory.subcategory_id GROUP BY (category.id) ) AS "subcategories_count",
	sh1(C.ID) AS "hash_sh1"
FROM
	caballinus_source.category C
WHERE
	(1=1)
