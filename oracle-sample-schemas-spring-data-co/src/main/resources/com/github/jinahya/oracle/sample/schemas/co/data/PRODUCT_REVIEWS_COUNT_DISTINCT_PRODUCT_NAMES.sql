-- ProductReviewRepository#countDistinctProductNames()
-- The view is qualified with its schema: hibernate.default_schema does not reach this statement.
SELECT COUNT(DISTINCT PRODUCT_NAME)
FROM CO.PRODUCT_REVIEWS
