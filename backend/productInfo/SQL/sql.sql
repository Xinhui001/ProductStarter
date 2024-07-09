create table productstarter
(
    id            int auto_increment comment '产品id'
        primary key,
    productNum    varchar(256)                       null comment '产品编号',
    showPic       varchar(256)                       null comment '产品展示图',
    showAll       varchar(256)                       null comment '全景展示',
    socketPic     varchar(256)                       null comment '调节器插口图（三张以内）',
    dataPic       int                                null comment '整机数据图（三张以内）',
    quality       int      default 0                 null comment '产品性质  0-全新  1-再制造 2-未知',
    applicationId int                                null comment '适用车型',
    serialNum     int                                null comment '系列号',
    seriesNum     int                                null comment '序列号',
    area          varchar(128)                       null comment '地区',
    parameterId   int                                null comment '产品参数id',
    createTime    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '产品信息表-起动机';

create table car
(
    applicationId int auto_increment comment 'id'
        primary key,
    carName       varchar(128)                       null comment '车型名称',
    carInfo       varchar(512)                       null comment '车型详情',
    manufacturer  varchar(128)                       null comment '生产厂商（品牌）',
    createTime    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '车型信息';

create table parameter
(
    parameterId      int auto_increment comment 'id'
        primary key,
    voltage          varchar(128)                       null comment '电压',
    power            varchar(128)                       null comment '功率',
    functionInfo     varchar(512)                       null comment '调节器功能',
    sizeA            int                                null comment '尺寸A',
    sizeB            int                                null comment '尺寸B',
    OD1              int                                null comment 'OD1（mm）',
    D1               int                                null comment 'D1 （mm）',
    D2               int                                null comment 'D2（mm）',
    D3               int                                null comment 'D3（mm）',
    D4               int                                null comment 'D4（mm）',
    L1               int                                null comment 'L1（mm）',
    createTime       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    productStarterId int                                not null comment '起动机id',
    constraint parameter_pk
        unique (productStarterId)
)
    comment '产品参数';