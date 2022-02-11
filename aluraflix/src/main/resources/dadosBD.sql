create table video(
    id BIGINT IDENTITY(1,1),
    idCategoria BIGINT NOT NULL,
    titulo TEXT NOT NULL,
    descricao TEXT NOT NULL,
    url TEXT NOT NULL,
    CONSTRAINT video_pk PRIMARY KEY(id),
    CONSTRAINT categoria_video_fk FOREIGN KEY (idCategoria)
        REFERENCES categoria(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE

);

create table categoria(
    id BIGINT IDENTITY(1,1),
    titulo TEXT NOT NULL,
    cor TEXT NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY(id)
)
