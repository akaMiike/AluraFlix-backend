create table video(
    id BIGINT IDENTITY(1,1),
    titulo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    url TEXT NOT NULL,
    CONSTRAINT video_pk PRIMARY KEY(id)
)
