/**
 * Copyright (c) 2015, CodiLime, Inc.
 *
 * Owner: Michal Iwanowski
 */

package io.deepsense.deeplang.catalogs.doperations

import java.util.UUID

/**
 * Category of DOperations.
 * It can be subcategory of different DOperationCategory.
 * @param id id of this category
 * @param name display name of this category
 * @param parent super-category of this one, if None this is top-level category
 *
 * TODO use global id class when available
 */
abstract class DOperationCategory(
    val id: UUID,
    val name: String,
    val parent: Option[DOperationCategory] = None) {

  def this(id: UUID, name: String, parent: DOperationCategory) = this(id, name, Some(parent))

  /** List of categories on path from this category to some top-level category. */
  private[doperations] def pathToRoot: List[DOperationCategory] = parent match {
    case Some(category) => this :: category.pathToRoot
    case None => List(this)
  }

  /** List of categories on path from some top-level category to this category. */
  private[doperations] def pathFromRoot: List[DOperationCategory] = pathToRoot.reverse
}