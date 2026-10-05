-- ProductReviewRepository#findDistinctProductNamesOfProductReviewsOrderByAvgRatingAsc(long, int)
-- The view is qualified with its schema: hibernate.default_schema does not reach this statement.
-- AVG_RATING is an average over PRODUCT_NAME, so a name has one; DISTINCT needs it selected to order by it.
-- A product with no reviews has no AVG_RATING, and comes last; PRODUCT_NAME breaks ties, so pages are stable.
SELECT DISTINCT PRODUCT_NAME, AVG_RATING
FROM CO.PRODUCT_REVIEWS
ORDER BY AVG_RATING ASC NULLS LAST, PRODUCT_NAME
OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY
