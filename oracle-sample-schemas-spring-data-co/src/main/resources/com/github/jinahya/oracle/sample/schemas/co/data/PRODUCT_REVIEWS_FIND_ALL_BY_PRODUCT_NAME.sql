-- ProductReviewRepository#findAllByProductName(String, long, int)
-- The view is qualified with its schema: hibernate.default_schema does not reach this statement.
-- The view has no key; the order is fixed so that consecutive pages neither repeat nor skip rows.
SELECT *
FROM CO.PRODUCT_REVIEWS
WHERE PRODUCT_NAME = :productName
ORDER BY RATING, REVIEW
OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY
