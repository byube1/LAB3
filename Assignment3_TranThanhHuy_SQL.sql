USE [master]
GO
/****** Object:  Database [Assignment3_TranThanhHuy]    Script Date: 3/7/2021 6:09:21 PM ******/
CREATE DATABASE [Assignment3_TranThanhHuy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Assignment3_TranThanhHuy', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Assignment3_TranThanhHuy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Assignment3_TranThanhHuy_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Assignment3_TranThanhHuy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Assignment3_TranThanhHuy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET RECOVERY FULL 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Assignment3_TranThanhHuy', N'ON'
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET QUERY_STORE = OFF
GO
USE [Assignment3_TranThanhHuy]
GO
/****** Object:  Table [dbo].[Car]    Script Date: 3/7/2021 6:09:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Car](
	[CarId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](200) NULL,
	[Bard] [nvarchar](200) NULL,
	[CarType] [nvarchar](200) NULL,
	[Color] [nvarchar](200) NULL,
	[YearManufacture] [nvarchar](200) NULL,
	[Price] [float] NULL,
	[Amount] [int] NULL,
	[Description] [nvarchar](200) NULL,
	[Img] [nvarchar](200) NULL,
	[CreateDate] [datetime] NULL,
	[Status] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[CarId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CarIsRenting]    Script Date: 3/7/2021 6:09:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CarIsRenting](
	[IdRenting] [int] IDENTITY(1,1) NOT NULL,
	[OderId] [int] NULL,
	[CarId] [int] NULL,
	[AmounrRenting] [int] NULL,
	[RentDate] [datetime] NULL,
	[ReturnDate] [datetime] NULL,
	[Price] [float] NULL,
	[Rate] [float] NULL,
	[Status] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdRenting] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 3/7/2021 6:09:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[DiscountCode] [nvarchar](10) NOT NULL,
	[Value] [float] NULL,
	[StartDate] [datetime] NULL,
	[EndDate] [datetime] NULL,
	[Description] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[DiscountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ordertbl]    Script Date: 3/7/2021 6:09:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ordertbl](
	[IdOrder] [int] NOT NULL,
	[EmailUser] [nvarchar](200) NULL,
	[Total] [float] NULL,
	[PaymentMenthod] [nvarchar](200) NULL,
	[DateCreate] [datetime] NULL,
	[Status] [nvarchar](20) NULL,
	[DiscountCode] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdOrder] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserInfo]    Script Date: 3/7/2021 6:09:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserInfo](
	[Email] [nvarchar](200) NOT NULL,
	[UserID] [nvarchar](200) NOT NULL,
	[Password] [nvarchar](200) NULL,
	[UserName] [nvarchar](200) NULL,
	[Phone] [nvarchar](200) NULL,
	[Address] [nvarchar](200) NULL,
	[Role] [nvarchar](200) NULL,
	[Status] [nvarchar](200) NULL,
	[CreateDate] [datetime] NULL,
	[Code] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Car] ON 
GO
INSERT [dbo].[Car] ([CarId], [Name], [Bard], [CarType], [Color], [YearManufacture], [Price], [Amount], [Description], [Img], [CreateDate], [Status]) VALUES (2, N'BMW Z4', N'BMW', N'Convertible', N'Red', N'2020', 1000, 2, N'Nice', N'img/BMWRed.png', CAST(N'2021-02-17T17:53:03.000' AS DateTime), N'Available')
GO
INSERT [dbo].[Car] ([CarId], [Name], [Bard], [CarType], [Color], [YearManufacture], [Price], [Amount], [Description], [Img], [CreateDate], [Status]) VALUES (3, N'Mercedes E200', N'Mercedes', N'Sedan', N'Black', N'2020', 1200, 5, N'Nice', N'img/MecBlack.png', CAST(N'2021-02-17T18:02:09.230' AS DateTime), N'Available')
GO
INSERT [dbo].[Car] ([CarId], [Name], [Bard], [CarType], [Color], [YearManufacture], [Price], [Amount], [Description], [Img], [CreateDate], [Status]) VALUES (4, N'Mercedes S63 4Matic', N'Mercedes', N'Sedan', N'Black', N'2020', 1800, 5, N'Nice', N'img/MecBlack2.png', CAST(N'2021-02-17T18:04:17.340' AS DateTime), N'Available')
GO
INSERT [dbo].[Car] ([CarId], [Name], [Bard], [CarType], [Color], [YearManufacture], [Price], [Amount], [Description], [Img], [CreateDate], [Status]) VALUES (5, N'Mercedes C180', N'Mercedes', N'Sedan', N'Black', N'2019', 1100, 1, N'Nice', N'img/MecBlack3.png', CAST(N'2021-02-17T18:05:50.567' AS DateTime), N'Available')
GO
INSERT [dbo].[Car] ([CarId], [Name], [Bard], [CarType], [Color], [YearManufacture], [Price], [Amount], [Description], [Img], [CreateDate], [Status]) VALUES (6, N'BMW 530i', N'BMW', N'Sedan', N'White', N'2019', 1150, 3, N'Nice', N'img/BMWWhite.png', CAST(N'2021-02-17T18:09:36.960' AS DateTime), N'Available')
GO
INSERT [dbo].[Car] ([CarId], [Name], [Bard], [CarType], [Color], [YearManufacture], [Price], [Amount], [Description], [Img], [CreateDate], [Status]) VALUES (7, N'BMW 730Li', N'BMW', N'Sedan', N'White', N'2020', 1120, 2, N'Nice', N'https://img.tinbanxe.vn/webp/images/BNW730Li/mau-xe-bmw-730li-black-sapphire-metallic.png', CAST(N'2021-02-17T18:13:55.640' AS DateTime), N'Available')
GO
SET IDENTITY_INSERT [dbo].[Car] OFF
GO
SET IDENTITY_INSERT [dbo].[CarIsRenting] ON 
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (22, 29041, 2, 1, CAST(N'2021-03-08T00:00:00.000' AS DateTime), CAST(N'2021-03-09T00:00:00.000' AS DateTime), 1000, NULL, N'Inactive')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (23, 14427, 5, 1, CAST(N'2021-03-08T00:00:00.000' AS DateTime), CAST(N'2021-03-09T00:00:00.000' AS DateTime), 1100, NULL, N'Inactive')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (24, 39941, 2, 2, CAST(N'2021-10-03T00:00:00.000' AS DateTime), CAST(N'2021-10-04T00:00:00.000' AS DateTime), 1000, NULL, N'Inactive')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (25, 19468, 2, 2, CAST(N'2021-10-03T00:00:00.000' AS DateTime), CAST(N'2021-10-04T00:00:00.000' AS DateTime), 1000, NULL, N'Inactive')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (26, 19468, 3, 1, CAST(N'2021-10-03T00:00:00.000' AS DateTime), CAST(N'2021-10-04T00:00:00.000' AS DateTime), 1200, NULL, N'Inactive')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (27, 8871, 2, 2, CAST(N'2021-10-03T00:00:00.000' AS DateTime), CAST(N'2021-10-04T00:00:00.000' AS DateTime), 1000, NULL, N'Active')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (28, 58060, 2, 1, CAST(N'2021-11-04T00:00:00.000' AS DateTime), CAST(N'2021-01-05T00:00:00.000' AS DateTime), 1000, 8.6000003814697266, N'Active')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (29, 58060, 3, 2, CAST(N'2021-11-04T00:00:00.000' AS DateTime), CAST(N'2021-01-05T00:00:00.000' AS DateTime), 1200, 7.1999998092651367, N'Active')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (30, 58060, 4, 1, CAST(N'2021-11-04T00:00:00.000' AS DateTime), CAST(N'2021-11-05T00:00:00.000' AS DateTime), 1800, NULL, N'Active')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (31, 12107, 3, 5, CAST(N'2022-10-03T00:00:00.000' AS DateTime), CAST(N'2022-10-04T00:00:00.000' AS DateTime), 1200, NULL, N'Inactive')
GO
INSERT [dbo].[CarIsRenting] ([IdRenting], [OderId], [CarId], [AmounrRenting], [RentDate], [ReturnDate], [Price], [Rate], [Status]) VALUES (32, 12107, 2, 1, CAST(N'2022-10-03T00:00:00.000' AS DateTime), CAST(N'2022-10-04T00:00:00.000' AS DateTime), 1000, NULL, N'Inactive')
GO
SET IDENTITY_INSERT [dbo].[CarIsRenting] OFF
GO
INSERT [dbo].[Discount] ([DiscountCode], [Value], [StartDate], [EndDate], [Description]) VALUES (N'GIAM10', 0.1, CAST(N'2021-03-06T00:00:00.000' AS DateTime), CAST(N'2021-03-08T00:00:00.000' AS DateTime), N'')
GO
INSERT [dbo].[Discount] ([DiscountCode], [Value], [StartDate], [EndDate], [Description]) VALUES (N'GIAM50', 0.5, CAST(N'2021-03-06T00:00:00.000' AS DateTime), CAST(N'2021-04-02T00:00:00.000' AS DateTime), N'')
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (8871, N'tieurua26@gmail.com', 2000, N'Cash payment upon delivery', CAST(N'2021-03-07T15:20:05.817' AS DateTime), N'Active', NULL)
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (12107, N'huyttse140652@fpt.edu.vn', 3500, N'Cash payment upon delivery', CAST(N'2021-03-07T17:46:07.250' AS DateTime), N'Inactive', N'GIAM50 ')
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (14427, N'tieurua26@gmail.com', 1100, N'Cash payment upon delivery', CAST(N'2021-03-07T15:15:11.677' AS DateTime), N'Inactive', NULL)
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (19468, N'tieurua26@gmail.com', 3200, N'Cash payment upon delivery', CAST(N'2021-03-07T15:18:16.530' AS DateTime), N'Inactive', NULL)
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (29041, N'tieurua26@gmail.com', 1000, N'Cash payment upon delivery', CAST(N'2021-03-07T15:14:26.347' AS DateTime), N'Inactive', NULL)
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (39941, N'tieurua26@gmail.com', 2000, N'Cash payment upon delivery', CAST(N'2021-03-07T15:16:37.123' AS DateTime), N'Inactive', NULL)
GO
INSERT [dbo].[Ordertbl] ([IdOrder], [EmailUser], [Total], [PaymentMenthod], [DateCreate], [Status], [DiscountCode]) VALUES (58060, N'tieurua26@gmail.com', 2600, N'Cash payment upon delivery', CAST(N'2021-03-07T16:08:55.603' AS DateTime), N'Active', N'GIAM50 ')
GO
INSERT [dbo].[UserInfo] ([Email], [UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate], [Code]) VALUES (N'ad@gmail.com', N'admin', N'123', N'admintration', N'123456', N'TP', N'Admin', N'Active', CAST(N'2021-02-17T15:28:36.453' AS DateTime), NULL)
GO
INSERT [dbo].[UserInfo] ([Email], [UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate], [Code]) VALUES (N'huyttse140652@fpt.edu.vn', N'huytt', N'123', N'huy', N'1234567890', N'asdwwww', N'User', N'Active', CAST(N'2021-03-07T17:44:01.913' AS DateTime), 6653)
GO
INSERT [dbo].[UserInfo] ([Email], [UserID], [Password], [UserName], [Phone], [Address], [Role], [Status], [CreateDate], [Code]) VALUES (N'tieurua26@gmail.com', N'huy', N'123', N'hht', N'1231234321', N'1aswdw', N'User', N'Active', CAST(N'2021-03-04T09:59:41.243' AS DateTime), 5910)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UserInfo__1788CCAD32A93243]    Script Date: 3/7/2021 6:09:21 PM ******/
ALTER TABLE [dbo].[UserInfo] ADD UNIQUE NONCLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CarIsRenting] ADD  CONSTRAINT [df_stt_carisrent]  DEFAULT (N'Active') FOR [Status]
GO
ALTER TABLE [dbo].[CarIsRenting]  WITH CHECK ADD FOREIGN KEY([CarId])
REFERENCES [dbo].[Car] ([CarId])
GO
ALTER TABLE [dbo].[CarIsRenting]  WITH CHECK ADD FOREIGN KEY([OderId])
REFERENCES [dbo].[Ordertbl] ([IdOrder])
GO
ALTER TABLE [dbo].[Ordertbl]  WITH CHECK ADD FOREIGN KEY([DiscountCode])
REFERENCES [dbo].[Discount] ([DiscountCode])
GO
ALTER TABLE [dbo].[Ordertbl]  WITH CHECK ADD FOREIGN KEY([EmailUser])
REFERENCES [dbo].[UserInfo] ([Email])
GO
USE [master]
GO
ALTER DATABASE [Assignment3_TranThanhHuy] SET  READ_WRITE 
GO
