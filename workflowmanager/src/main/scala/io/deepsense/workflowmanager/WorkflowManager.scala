/**
 * Copyright (c) 2015, CodiLime Inc.
 */

package io.deepsense.workflowmanager

import scala.concurrent.Future

import org.joda.time.DateTime

import io.deepsense.commons.models.Id
import io.deepsense.graph.Node
import io.deepsense.models.workflows._

/**
 * Workflow Manager's API
 */
trait WorkflowManager {

  /**
   * Returns a workflow with results with the specified Id.
   * @param id An identifier of the workflow.
   * @return A workflow with results with the specified Id.
   */
  def get(id: Id): Future[Option[WorkflowWithResults]]

  /**
   * Returns a workflow with an empty variables section and specified Id.
   * @param id An identifier of the workflow.
   * @return A workflow with an empty variables section and specified Id.
   */
  def download(id: Id): Future[Option[WorkflowWithVariables]]

  /**
   * Updates an workflow.
   * @param workflowId Id of workflow to be updated.
   * @param workflow An workflow to be updated.
   * @return The updated workflow with knowledge.
   */
  def update(workflowId: Id, workflow: Workflow): Future[Unit]

  /**
   * Creates new workflow.
   * @param workflow New workflow.
   * @return created workflow id.
   */
  def create(workflow: Workflow): Future[Workflow.Id]

  /**
   * Deletes a workflow by Id.
   * @param id An identifier of the workflow to delete.
   * @return True if the workflow was deleted.
   *         Otherwise false.
   */
  def delete(id: Id): Future[Boolean]

  /**
   * Lists stored workflows.
   * @return List of basic information about stored workflows.
   */
  def list(): Future[Seq[WorkflowInfo]]

  /**
   * Saves workflow results
   * @param workflowWithResults workflow results to save
   * @return saved workflow results
   */
  def saveWorkflowResults(
    workflowWithResults: WorkflowWithResults): Future[WorkflowWithSavedResults]

  /**
   * Gets execution report by id. If the workflow is compatible with the current API version
   * it is returned as an object otherwise as a string.
   * @param id workflow execution report id
   * @return workflow execution report
   */
  def getExecutionReport(id: ExecutionReportWithId.Id):
    Future[Option[Either[String, WorkflowWithSavedResults]]]

  /**
   * Returns latest execution report for workflow with given id.
   * @param workflowId id of the workflow.
   * @return Latest execution report.
   */
  def getLatestExecutionReport(workflowId: Workflow.Id):
    Future[Option[Either[String, WorkflowWithSavedResults]]]

  /**
   * Returns the upload time of the recently uploaded execution report of the specified workflow.
   * @param workflowId An identifier of the workflow to return the execution report' upload time.
   * @return The upload time or None if the workflow does not exist or no execution report exist
   *         for the specified id.
   */
  def getResultsUploadTime(workflowId: Workflow.Id): Future[Option[DateTime]]

  /**
   * Returns a notebook for workflow with the specified id.
   *
   * @param workflowId Id of the workflow.
   * @param nodeId Id of the node.
   * @return Notebook or None if the notebook does not exist.
   */
  def getNotebook(workflowId: Workflow.Id, nodeId: Node.Id): Future[Option[String]]

  /**
   * Saves a notebook for workflow with the specified id.
   *
   * @param workflowId Id of the workflow.
   * @param nodeId Id of the node.
   * @param notebook Notebook to be saved.
   */
  def saveNotebook(workflowId: Workflow.Id, nodeId: Node.Id, notebook: String): Future[Unit]

  /**
   * Updates an workflow, and then updates its nodes states in WorkflowStateStorage.
   *
   * @param wfId Id of the workflow.
   * @param wfWithResults Updated workflow and its updated results to save.
   */
  def updateStructAndStates(wfId: Workflow.Id, wfWithResults: WorkflowWithResults): Future[Unit]
}
