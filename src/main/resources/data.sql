INSERT INTO inventory (id, product_name, stock)
VALUES
    (1, 'Iphone', 10),
    (2, 'Samsung', 20)
    ON CONFLICT (id)
DO UPDATE SET
    product_name = EXCLUDED.product_name,
           stock = EXCLUDED.stock;