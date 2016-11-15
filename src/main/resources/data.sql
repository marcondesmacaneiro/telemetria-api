INSERT INTO public.tb_sensor_leitura (id,nome) 
SELECT 1, 'Nível do Rio'
WHERE
    NOT EXISTS (
        SELECT id FROM public.tb_sensor_leitura WHERE id = 1
    );
INSERT INTO public.tb_sensor_leitura (id,nome) 
SELECT 2, 'Umidade'
WHERE
    NOT EXISTS (
        SELECT id FROM public.tb_sensor_leitura WHERE id = 2
    );
INSERT INTO public.tb_sensor_leitura (id,nome) 
SELECT 3, 'Vento'
WHERE
    NOT EXISTS (
        SELECT id FROM public.tb_sensor_leitura WHERE id = 3
    );
INSERT INTO public.tb_sensor_leitura (id,nome) 
SELECT 4, 'Temperatura'
WHERE
    NOT EXISTS (
        SELECT id FROM public.tb_sensor_leitura WHERE id = 4
    );