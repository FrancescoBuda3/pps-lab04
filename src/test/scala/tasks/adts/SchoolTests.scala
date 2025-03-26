package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import u03.extensionmethods.Sequences.Sequence
import u03.extensionmethods.Sequences.Sequence.{cons, nil}

/* Tests should be clear, but note they are expressed independently of the 
   specific implementation
*/

class SchoolTests:

  val school: SchoolModule = BasicSchoolModule
  import school.*

  @Test def emptySchoolHasNoCourses(): Unit =
    assertEquals(nil(), emptySchool.courses)

  @Test def emptySchoolHasNoTeachers(): Unit =
    assertEquals(nil(), emptySchool.teachers)

  @Test def returnsCourses(): Unit =
    assertEquals(cons("Math", nil()), emptySchool.setTeacherToCourse(teacher("John"), course("Math")).courses)
    assertEquals(cons("Italian", cons("Math", nil())), emptySchool
      .setTeacherToCourse(teacher("John"), course("Math"))
      .setTeacherToCourse(teacher("John"), course("Italian")).courses
    )
    
  @Test def returnsCoursesOfATeacher(): Unit =
    assertEquals(nil(), emptySchool.coursesOfATeacher(teacher("John")))
    assertEquals(cons(course("Math"), nil()), emptySchool
      .setTeacherToCourse(teacher("John"), course("Math"))
      .coursesOfATeacher(teacher("John"))
    )
    assertEquals(cons(course("Italian"), cons(course("Math"), nil())), emptySchool
      .setTeacherToCourse(teacher("John"), course("Math"))
      .setTeacherToCourse(teacher("John"), course("Italian"))
      .coursesOfATeacher(teacher("John"))
    )

  @Test def hasCourse(): Unit =
    assertFalse(emptySchool.hasCourse("Math"))
    assertTrue(emptySchool
      .setTeacherToCourse(teacher("John"), course("Math"))
      .hasCourse("Math")
    )

  @Test def hasTeacher(): Unit =
    assertFalse(emptySchool.hasTeacher("John"))
    assertTrue(emptySchool
      .setTeacherToCourse(teacher("John"), course("Math"))
      .hasTeacher("John"))
