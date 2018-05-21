USE [master]
GO
/****** Object:  Database [MobileXD]    Script Date: 21/5/2018 7:32:04 PM ******/
CREATE DATABASE [MobileXD]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MobileXD', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\MobileXD.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'MobileXD_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\MobileXD_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [MobileXD] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MobileXD].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MobileXD] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MobileXD] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MobileXD] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MobileXD] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MobileXD] SET ARITHABORT OFF 
GO
ALTER DATABASE [MobileXD] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MobileXD] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MobileXD] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MobileXD] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MobileXD] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MobileXD] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MobileXD] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MobileXD] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MobileXD] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MobileXD] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MobileXD] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MobileXD] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MobileXD] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MobileXD] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MobileXD] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MobileXD] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MobileXD] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MobileXD] SET RECOVERY FULL 
GO
ALTER DATABASE [MobileXD] SET  MULTI_USER 
GO
ALTER DATABASE [MobileXD] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MobileXD] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MobileXD] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MobileXD] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [MobileXD] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'MobileXD', N'ON'
GO
USE [MobileXD]
GO
/****** Object:  Table [dbo].[tblAccount]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblAccount](
	[username] [nvarchar](100) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[role] [int] NOT NULL,
	[personID] [int] NULL,
 CONSTRAINT [PK_tblAccount] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblClass]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblClass](
	[classID] [int] NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[quantity] [int] NULL,
	[courseID] [int] NOT NULL,
	[teacherID] [int] NOT NULL,
	[roomID] [int] NOT NULL,
	[slotID] [int] NOT NULL,
	[weekdayID] [int] NOT NULL,
 CONSTRAINT [PK_tblClass] PRIMARY KEY CLUSTERED 
(
	[classID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblCourse]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCourse](
	[courseID] [int] NOT NULL,
	[math] [bit] NULL,
	[physical] [bit] NULL,
	[chemistry] [bit] NULL,
	[english] [bit] NULL,
 CONSTRAINT [PK_tblCourse] PRIMARY KEY CLUSTERED 
(
	[courseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblLearning]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblLearning](
	[learningID] [int] NOT NULL,
	[studentID] [int] NOT NULL,
	[classID] [int] NOT NULL,
	[slotID] [int] NOT NULL,
	[weekdayID] [int] NOT NULL,
 CONSTRAINT [PK_tblLearning] PRIMARY KEY CLUSTERED 
(
	[learningID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRoom]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoom](
	[roomID] [int] NOT NULL,
	[roomNumber] [int] NOT NULL,
	[floor] [int] NULL,
 CONSTRAINT [PK_tblRoom] PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblSlot]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSlot](
	[slotID] [int] NOT NULL,
	[slot1] [bit] NULL,
	[slot2] [bit] NULL,
	[slot3] [bit] NULL,
	[slot4] [bit] NULL,
	[slot5] [bit] NULL,
 CONSTRAINT [PK_tblSlot] PRIMARY KEY CLUSTERED 
(
	[slotID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblStudent]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStudent](
	[studentID] [int] NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[birthday] [date] NOT NULL,
	[sex] [nvarchar](20) NOT NULL,
	[nameParent] [nvarchar](100) NOT NULL,
	[phoneStudent] [nvarchar](11) NULL,
	[phoneParent] [nvarchar](11) NULL,
 CONSTRAINT [PK_tblStudent] PRIMARY KEY CLUSTERED 
(
	[studentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblTeacher]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTeacher](
	[teacherID] [int] NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[birthday] [date] NOT NULL,
	[sex] [nvarchar](20) NOT NULL,
	[address] [nvarchar](100) NULL,
	[specialize] [nvarchar](50) NOT NULL,
	[salary] [int] NULL,
 CONSTRAINT [PK_tblTeacher] PRIMARY KEY CLUSTERED 
(
	[teacherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblWeekday]    Script Date: 21/5/2018 7:32:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblWeekday](
	[weekdayID] [int] NOT NULL,
	[monday] [bit] NULL,
	[tuesday] [bit] NULL,
	[wednesday] [bit] NULL,
	[thurday] [bit] NULL,
	[friday] [bit] NULL,
	[saturday] [bit] NULL,
	[sunday] [bit] NULL,
 CONSTRAINT [PK_tblWeekday] PRIMARY KEY CLUSTERED 
(
	[weekdayID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[tblAccount]  WITH CHECK ADD  CONSTRAINT [FK_tblAccount_tblStudent] FOREIGN KEY([personID])
REFERENCES [dbo].[tblStudent] ([studentID])
GO
ALTER TABLE [dbo].[tblAccount] CHECK CONSTRAINT [FK_tblAccount_tblStudent]
GO
ALTER TABLE [dbo].[tblAccount]  WITH CHECK ADD  CONSTRAINT [FK_tblAccount_tblTeacher] FOREIGN KEY([personID])
REFERENCES [dbo].[tblTeacher] ([teacherID])
GO
ALTER TABLE [dbo].[tblAccount] CHECK CONSTRAINT [FK_tblAccount_tblTeacher]
GO
ALTER TABLE [dbo].[tblClass]  WITH CHECK ADD  CONSTRAINT [FK_tblClass_tblCourse] FOREIGN KEY([courseID])
REFERENCES [dbo].[tblCourse] ([courseID])
GO
ALTER TABLE [dbo].[tblClass] CHECK CONSTRAINT [FK_tblClass_tblCourse]
GO
ALTER TABLE [dbo].[tblClass]  WITH CHECK ADD  CONSTRAINT [FK_tblClass_tblRoom] FOREIGN KEY([roomID])
REFERENCES [dbo].[tblRoom] ([roomID])
GO
ALTER TABLE [dbo].[tblClass] CHECK CONSTRAINT [FK_tblClass_tblRoom]
GO
ALTER TABLE [dbo].[tblClass]  WITH CHECK ADD  CONSTRAINT [FK_tblClass_tblSlot] FOREIGN KEY([slotID])
REFERENCES [dbo].[tblSlot] ([slotID])
GO
ALTER TABLE [dbo].[tblClass] CHECK CONSTRAINT [FK_tblClass_tblSlot]
GO
ALTER TABLE [dbo].[tblClass]  WITH CHECK ADD  CONSTRAINT [FK_tblClass_tblTeacher] FOREIGN KEY([teacherID])
REFERENCES [dbo].[tblTeacher] ([teacherID])
GO
ALTER TABLE [dbo].[tblClass] CHECK CONSTRAINT [FK_tblClass_tblTeacher]
GO
ALTER TABLE [dbo].[tblClass]  WITH CHECK ADD  CONSTRAINT [FK_tblClass_tblWeekday] FOREIGN KEY([weekdayID])
REFERENCES [dbo].[tblWeekday] ([weekdayID])
GO
ALTER TABLE [dbo].[tblClass] CHECK CONSTRAINT [FK_tblClass_tblWeekday]
GO
ALTER TABLE [dbo].[tblLearning]  WITH CHECK ADD  CONSTRAINT [FK_tblLearning_tblClass] FOREIGN KEY([classID])
REFERENCES [dbo].[tblClass] ([classID])
GO
ALTER TABLE [dbo].[tblLearning] CHECK CONSTRAINT [FK_tblLearning_tblClass]
GO
ALTER TABLE [dbo].[tblLearning]  WITH CHECK ADD  CONSTRAINT [FK_tblLearning_tblSlot] FOREIGN KEY([slotID])
REFERENCES [dbo].[tblSlot] ([slotID])
GO
ALTER TABLE [dbo].[tblLearning] CHECK CONSTRAINT [FK_tblLearning_tblSlot]
GO
ALTER TABLE [dbo].[tblLearning]  WITH CHECK ADD  CONSTRAINT [FK_tblLearning_tblStudent] FOREIGN KEY([studentID])
REFERENCES [dbo].[tblStudent] ([studentID])
GO
ALTER TABLE [dbo].[tblLearning] CHECK CONSTRAINT [FK_tblLearning_tblStudent]
GO
ALTER TABLE [dbo].[tblLearning]  WITH CHECK ADD  CONSTRAINT [FK_tblLearning_tblWeekday] FOREIGN KEY([weekdayID])
REFERENCES [dbo].[tblWeekday] ([weekdayID])
GO
ALTER TABLE [dbo].[tblLearning] CHECK CONSTRAINT [FK_tblLearning_tblWeekday]
GO
USE [master]
GO
ALTER DATABASE [MobileXD] SET  READ_WRITE 
GO
