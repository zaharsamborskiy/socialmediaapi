insert into social_media_api_db.public.roles (id,name)
values
    (0,'ROLE_USER'), (-1,'ROLE_ADMIN');

insert into social_media_api_db.public.users (id,username, password, email)
values
    (0,'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
    (-1,'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into social_media_api_db.public.users_roles (user_id, role_id)
values
    (0, 0),
    (-1, -1);