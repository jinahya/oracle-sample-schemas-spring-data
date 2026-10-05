-- ProductReviewRepository#countByProductName(String)
-- The view is qualified with its schema: hibernate.default_schema does not reach this statement.
SELECT COUNT(*)
FROM CO.PRODUCT_REVIEWS
WHERE PRODUCT_NAME = :productName
